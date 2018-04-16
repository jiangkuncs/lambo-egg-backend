package com.lambo.oss.client.controller;

import com.lambo.common.base.BaseController;
import com.lambo.common.util.FileUtil;
import com.lambo.oss.client.constant.OssConstant;
import com.lambo.oss.client.constant.OssResult;
import com.lambo.oss.client.constant.OssResultConstant;
import com.lambo.oss.client.service.api.OssClientApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 对象存储相关服务
 * Created by sunzhen on 2018/4/10.
 */
@Controller
@Api(value = "对象存储相关", description = "对象存储相关")
@RequestMapping("/manage")
public class OssClientApiController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(OssClientApiController.class);

    @Autowired
    OssClientApiService ossClientApiService;


    @ApiOperation(value = "保存文件" ,notes = "保存文件")
    @RequestMapping(value = "/file/put", method = RequestMethod.POST)
    @ResponseBody
    public Object put(@RequestParam("file") MultipartFile[] files) {

        List list = new ArrayList();

        //判断file数组不能为空并且长度大于0
        if (files != null && files.length > 0) {
            //循环获取file数组中得文件
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                //保存文件
                Map<String,Object> saveResultMap = ossClientApiService.saveFile(file);
                list.add(saveResultMap);
            }
        }else{
            return new OssResult(OssResultConstant.FAILED,"文件上传出错");
        }

        return new OssResult(OssResultConstant.SUCCESS,list);


    }

    @ApiOperation(value = "获取文件" ,notes = "获取文件")
    @RequestMapping(value = "/file/get/{name:.+}", method = RequestMethod.GET)
    @ResponseBody
    public void get(@PathVariable("name") String name,HttpServletRequest request,HttpServletResponse response) {

        String filePath = FileUtil.path(FileUtil.getWebappPath()
                + OssConstant.UPLOAD_TEMP_PATH)
                + File.separator + name;


        File file = new File(filePath);

        FileUtil.downFile(file,request,response);

    }
}