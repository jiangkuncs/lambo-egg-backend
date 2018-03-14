package com.lambo.ndp.controller;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lambo.common.annotation.EnableExportTable;
import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.common.validator.LengthValidator;
import com.lambo.ndp.constant.NdpResult;
import com.lambo.ndp.constant.NdpResultConstant;
import com.lambo.ndp.model.CateGory;
import com.lambo.ndp.model.Dict;
import com.lambo.ndp.model.Table;
import com.lambo.ndp.model.TableCell;
import com.lambo.ndp.service.api.TableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
import org.apache.poi.ss.formula.functions.Columns;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.lambo.ndp.service.api.DictService;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Character.getType;

/**
 * 日志controller
 * Created by zxc
 */
@Controller
@Api(value = "库表查询列表", description = "库表查询列表")
@RequestMapping("/manage/tabledata")
public class TableController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(TableController.class);

    @Autowired
    private TableService tableService;
    @Autowired
    private DictService DictService;

    @ApiOperation(value = "库表列表数据")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    @EnableExportTable
    @LogAround("请求列表数据")
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "tableCode") String tableCode,
            @RequestParam(required = false, value = "tableName") String tableName) {

        Map<String,Object> param = new HashMap<String, Object>();
        param.put("tableCode",tableCode);
        param.put("tableName",tableName);
        //物理分页
        PageHelper.offsetPage(offset, limit);
        List data = tableService.queryTable(param);
        PageInfo page = new PageInfo(data);

        Map<String, Object> result = new HashMap<>();
        result.put("rows", page.getList());
        result.put("total", page.getTotal());
        return new NdpResult(NdpResultConstant.SUCCESS,result);
    }
    @ApiOperation(value = "数据元列表数据")
    @RequestMapping(value = "/listtablecell",method = RequestMethod.POST)
    @ResponseBody
    @EnableExportTable
    @LogAround("请求列表数据")
    public Object listTableCell(

            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "100", value = "limit") int limit,
            @RequestParam(required = false, value = "tableId") int tableId) {

        Map<String,Object> param = new HashMap<String, Object>();
        //物理分页
        PageHelper.offsetPage(offset, limit);
        List data = tableService.queryTableCell(tableId);
//        JSONArray json = JSONArray.parseArray(data.toString());
//        String dictId="";
//        if(json.size()>0){
//            for(int i=0;i<json.size();i++){
//                JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
//                System.out.println("job:"+job) ;  // 得到 每个对象中的属性值
//                dictId=(String)job.get("dictId");
//                if(dictId==null || "".equals(dictId)){
//
//                }else{
//                    List<Dict> Dict = DictService.selectByDictId(dictId);
//
//                }
//
//            }
//        }
//        for(int i=0;i<data.size();i++){
          // System.out.println("data[]"+data.toString());
//            String dictId="1";
//            List<Dict> Dict = DictService.selectByDictId(dictId);
//        }
        PageInfo page = new PageInfo(data);

        Map<String, Object> result = new HashMap<>();
        result.put("rows", page.getList());
        result.put("total", page.getTotal());
        //System.out.println("result:"+page.getTotal());
        return new NdpResult(NdpResultConstant.SUCCESS,data);
    }
    @ApiOperation(value = "数据库列表数据")
    @RequestMapping(value = "/listDbTable",method = RequestMethod.GET)
    @ResponseBody
    @EnableExportTable
    @LogAround("列表数据")
    public Object listDbTable(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "tableName") String tableName) {

        Map<String,Object> param = new HashMap<String, Object>();
        //param.put("tableName",tableName);
        //物理分页
        PageHelper.offsetPage(offset, limit);
        List data = tableService.queryDbTable(param);
        PageInfo page = new PageInfo(data);

        Map<String, Object> result = new HashMap<>();
        result.put("rows", page.getList());
        result.put("total", page.getTotal());
        return new NdpResult(NdpResultConstant.SUCCESS,result);
    }
    @ApiOperation(value = "库表字段列表数据")
    @RequestMapping(value = "/listDbTableColumns",method = RequestMethod.GET)
    @ResponseBody
    @EnableExportTable
    @LogAround("列表数据")
    public Object listDbTableColumns(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = true, value = "tableName") String tableName,
            @RequestParam(required = false, value = "selectColumns") String selectColumns) {
        System.out.println("selectColumns="+selectColumns);

        Map<String,Object> param = new HashMap<String, Object>();
        param.put("tableName",tableName);
        List data = tableService.queryDbTableColumns(param);
        String Columns="";
        if(selectColumns==null || "".equals(selectColumns) || "[]".equals(selectColumns)){

        }else{
            Columns=selectColumns.replace("[","").replace("\"","").replace("]","");
        }

        //System.out.println("Columns="+Columns);

        //System.out.println("data="+data);
        String[] columnsArry= Columns.split(",");
        List<Map<String,String>> dataNew=new ArrayList<Map<String,String>>();
        String columnName="";
        Boolean is=true;
        for(int j=0;j<data.size();j++){
            Map parm=new HashMap();
            parm=(Map)data.get(j);
            columnName=(String)parm.get("COLUMN_NAME");
           // System.out.println("columnName="+columnName);
            for(int i=0;i<columnsArry.length;i++){
                if(columnsArry[i].equals(columnName)){
                    is=false;
                    break;
                }

            }
            if(is){
                dataNew.add(parm);
            }
            is=true;
        }
        //System.out.println("dataNew="+dataNew);
        PageHelper.offsetPage(offset, limit);
        PageInfo page = new PageInfo(dataNew);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", page.getList());
        result.put("total", page.getTotal());
        return new NdpResult(NdpResultConstant.SUCCESS,result);
    }
    @ApiOperation(value = "新增库表数据")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(
            @RequestParam(required = true, value = "tableCode") String tableCode,
            @RequestParam(required = true, value = "tableName") String tableName,
            @RequestParam(required = false, value = "tableDesc") String tableDesc,
            @RequestParam(required = false, value = "TableCellss" ) String TableCellss) {
        ComplexResult result = FluentValidator.checkAll()
                .on(tableCode, new LengthValidator(1, 50, "表名"))
                .on(tableName, new LengthValidator(0, 50, "中文名"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new NdpResult(NdpResultConstant.INVALID_LENGTH, result.getErrors());
        }
        Map<String,Object> param = new HashMap<String, Object>();
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        param.put("createUser",1);
        param.put("createTime",df.format(day).toString());
        param.put("tableCode",tableCode);
        param.put("tableName",tableName);
        param.put("tableDesc",tableDesc);
        Table table=new Table();
        table.setCreateTime(df.format(day).toString());
        table.setCreateUser(1);
        table.setTableCode(tableCode);
        table.setTableDesc(tableDesc);
        table.setTableName(tableName);
        int con =tableService.insertTable(table);
        if (con!=1) {
            return new NdpResult(NdpResultConstant.INVALID_LENGTH, result.getErrors());
        }
        int tableId=table.getTableId();
        //System.out.println("tableId="+tableId);
        //System.out.println("TableCellss:"+TableCellss);
        JSONArray json = JSONArray.parseArray(TableCellss);
        //System.out.println("json:"+json);
        if(json.size()>0){
            for(int i=0;i<json.size();i++){
                JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                System.out.println("job:"+job) ;  // 得到 每个对象中的属性值
                TableCell tableCell=new TableCell();
                tableCell.setTableId(tableId);
                tableCell.setCellCode((String) job.get("cellCode"));
                tableCell.setCellName((String) job.get("cellName"));
                tableCell.setDictId((String) job.get("dictId"));
                tableCell.setDataDesc((String) job.get("dataDesc"));
                tableCell.setDataUnit((String) job.get("dataUnit"));
                int conCell =tableService.insertTableCell(tableCell);

            }
        }
        return new NdpResult(NdpResultConstant.SUCCESS, con);
    }

    @ApiOperation(value = "更新库表")
    @RequestMapping(value = "/update/{tableId}", method = RequestMethod.POST)
    @ResponseBody
    public Object updateUser(
            @PathVariable("tableId") int tableId,
            @RequestParam(required = true, value = "tableCode") String tableCode,
            @RequestParam(required = true, value = "tableName") String tableName,
            @RequestParam(required = false, value = "tableDesc") String tableDesc,
            @RequestParam(required = false, value = "TableCellss" ) String TableCellss
    ){
        int count = 0;
        ComplexResult result = FluentValidator.checkAll()
                .on(tableCode, new LengthValidator(1, 50, "表名"))
                .on(tableName, new LengthValidator(0, 50, "中文名"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new NdpResult(NdpResultConstant.INVALID_LENGTH, result.getErrors());
        }
        if(tableId > 0){
            Map<String,Object> param = new HashMap<String, Object>();
            Date day=new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            param.put("tableId",tableId);
            param.put("tableCode",tableCode);
            param.put("tableName",tableName);
            param.put("createUser",1);
            param.put("tableDesc",tableDesc);
            param.put("createTime",df.format(day).toString());
            count = tableService.updateTable(param);
        }
        //System.out.println("TableCellss:"+TableCellss);
        count = tableService.deleteTableCellByTableId(tableId);
        JSONArray json = JSONArray.parseArray(TableCellss);
        //System.out.println("json:"+json);
        if(json.size()>0){
            for(int i=0;i<json.size();i++){
                JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                System.out.println("job:"+job) ;  // 得到 每个对象中的属性值
                TableCell tableCell=new TableCell();
                tableCell.setTableId(tableId);
                tableCell.setCellCode((String) job.get("cellCode"));
                tableCell.setCellName((String) job.get("cellName"));
                tableCell.setDictId((String) job.get("dictId"));
                tableCell.setDataDesc((String) job.get("dataDesc"));
                tableCell.setDataUnit((String) job.get("dataUnit"));
                int conCell =tableService.insertTableCell(tableCell);

            }
        }
        return new NdpResult(NdpResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "删除数据元")
    @RequestMapping(value = "/deleteTableCell", method = RequestMethod.GET)
    @ResponseBody
    public int deleteTableCell(
            @RequestParam(required = true, defaultValue = "", value = "cellId") int cellId
    ){
            int count = 0;
            count = tableService.deleteTableCellByPrimaryKey(cellId);
        return count;
    }
    @ApiOperation(value = "删除库表")
    @RequestMapping(value = "/deleteTable/{tableId}", method = RequestMethod.GET)
    @ResponseBody
    public int deleteTable(
            @PathVariable("tableId") int tableId
    ){
        //System.out.println("tableId="+tableId+",,,"+getType(tableId));
        int count = 0;
        count = tableService.deleteTableCellByTableId(tableId);
        tableService.deleteTableByTableId(tableId);
        return count;
    }
    @ApiOperation(value = "根据ID查询库表")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id") int id) {
        Table table = tableService.selectByPrimaryKey(id);
        return new NdpResult(NdpResultConstant.SUCCESS, table);
    }
}