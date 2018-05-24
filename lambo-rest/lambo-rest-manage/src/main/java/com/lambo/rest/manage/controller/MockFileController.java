package com.lambo.rest.manage.controller;

import com.lambo.common.base.BaseController;
import com.lambo.common.utils.io.FileUtils;
import com.lambo.common.utils.mapper.JsonMapper;
import com.lambo.oss.client.constant.OssConstant;
import com.lambo.oss.client.constant.OssResult;
import com.lambo.oss.client.constant.OssResultConstant;
import com.lambo.oss.client.service.api.OssClientApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.ObjectUtils.isEmpty;

/**
 * 对象存储相关服务
 * Created by sunzhen on 2018/4/10.
 */
@Controller
@Api(value = "MOKE数据上传下载服务", description = "MOKE数据上传下载服务")
@RequestMapping("/manage/mock/file")
public class MockFileController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(MockFileController.class);

    @Autowired
    OssClientApiService ossClientApiService;

    @ApiOperation(value = "保存文件" ,notes = "保存文件")
    @RequestMapping(value = "/put", method = RequestMethod.POST)
    @ResponseBody
    public Object put(@RequestParam("file") MultipartFile[] files) {
        logger.info("上传文件开始。。。");
        List dataList = new ArrayList();

        //判断file数组不能为空并且长度=1 单文件传输
        if (files != null && files.length > 0) {
            //单文件上传，故文件列表数为1
            MultipartFile file = files[0];
            //保存文件
            Map<String,Object> saveResultMap = ossClientApiService.saveFile(file);

            //解析excel文件
            String fileName = (String)saveResultMap.get("name");
            String filePath = FileUtils.path(FileUtils.getWebappPath() + OssConstant.UPLOAD_TEMP_PATH)+ fileName;

            logger.info("filePath="+filePath);

            File inputFile = new File(filePath);
            Workbook workbook = null;
            try {
                workbook = new XSSFWorkbook(inputFile);
                Sheet sheet = workbook.getSheetAt(0);

                int rowStart = sheet.getFirstRowNum();
                int rowEnd = sheet.getLastRowNum();

                //字段行
                Row fildRow = sheet.getRow(rowStart);
                int colStart = fildRow.getFirstCellNum();
                int colEnd = fildRow.getLastCellNum();
                int colNum = colEnd - colStart;

                String[] fildArr = new String[colNum];
                String[][] colType = new String[2][colNum];

                for(int i=0;i<colNum;i++){
                    String value = fildRow.getCell(i).getStringCellValue();
                    if(value.indexOf("|") != -1){
                        String[] valueArr = value.split("\\|");
                        fildArr[i] = valueArr[0].trim();
                        colType[0][i] = valueArr[1].trim();
                    }else{
                        fildArr[i] = value.trim();
                        colType[0][i] = "";
                    }
                }

                //数据行
                for(int i=rowStart+1;i<=rowEnd;i++){
                    Row dataRow = sheet.getRow(i);
                    Map dataMap = new HashMap();
                    for(int j=colStart;j<colEnd;j++){
                        Cell cell = dataRow.getCell(j);
                        if(!isEmpty(cell)){
                            if(null == colType[1][j]){
                                colType[1][j] = cell.getCellTypeEnum().toString();
                            }
                            if("STRING".equals(colType[1][j])){
                                String value = cell.getStringCellValue();
                                if(null != value){
                                    dataMap.put(fildArr[j],value);
                                }
                            }else{
                                Double doubleVal = cell.getNumericCellValue();
                                long longVal = Math.round(cell.getNumericCellValue());
                                Object inputValue = null;// 单元格值
                                if(Double.parseDouble(longVal + ".0") == doubleVal){
                                    inputValue = longVal;
                                }else{
                                    inputValue = doubleVal;
                                }
                                if(null != inputValue){
                                    if(("").equals(colType[0][j]) ||  (colType[0][j]).equals(colType[1][j])){
                                        dataMap.put(fildArr[j],inputValue);
                                    }else if(("STRING").equals(colType[0][j])){
                                        dataMap.put(fildArr[j],inputValue.toString());
                                    }
                                }
                            }
                        }
                    }
                    dataList.add(dataMap);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }finally{
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //删除上传文件
            logger.info("删除文件开始。。。");
            try{
                if (inputFile.delete()){
                    logger.info("删除文件成功。。。");
                }else{
                    logger.info("删除文件失败。。。");
                }
            }catch(Exception e){
                logger.info("删除文件异常e",e);
            }

            logger.info("删除文件结束。。。");
        }else{
            return new OssResult(OssResultConstant.FAILED,"文件上传出错");
        }

        logger.info("上传文件结束。。。");
        String returnJson = "";
        JsonMapper jsonMapper = new JsonMapper();
        if(dataList.size()==1){
            returnJson = jsonMapper.toJsonString(dataList.get(0));
        }else{
            returnJson = jsonMapper.toJsonString(dataList);
        }

        return new OssResult(OssResultConstant.SUCCESS, returnJson);

    }
}
