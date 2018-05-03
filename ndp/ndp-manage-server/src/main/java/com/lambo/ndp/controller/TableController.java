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

import com.lambo.common.utils.lang.StringUtils;
import com.lambo.common.validator.LengthValidator;
import com.lambo.ndp.constant.NdpResult;
import com.lambo.ndp.constant.NdpResultConstant;
import com.lambo.ndp.model.Table;
import com.lambo.ndp.model.TableCell;
import com.lambo.ndp.model.TableCellDict;
import com.lambo.ndp.service.api.TableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.poi.ss.formula.functions.Columns;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.transaction.annotation.Transactional;
import static java.lang.Character.getType;

/**
 * TableController控制层
 * Created by zxc on 2018/3/10.
 */
@Controller
@Api(value = "库表查询列表", description = "库表查询列表")
@RequestMapping("/manage/tableData")
public class TableController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(TableController.class);

    @Autowired
    private TableService tableService;
    @ApiOperation(value = "库表列表数据")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    @EnableExportTable
    @LogAround("请求列表数据")
    public Object listExport(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @ApiParam(name="sort", value = "排序字段")
            @RequestParam(required = false, value = "sort") String sort,
            @ApiParam(name="order", value = "排序方式")
            @RequestParam(required = false, value = "order") String order,
            @RequestParam(required = false, defaultValue = "", value = "tableCode") String tableCode,
            @RequestParam(required = false, value = "tableName") String tableName) {
        return ((NdpResult)list(offset,limit,sort,order,tableCode,tableName)).data;
    }
    @ApiOperation(value = "库表列表数据")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    @EnableExportTable
    @LogAround("请求列表数据")
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @ApiParam(name="sort", value = "排序字段")
            @RequestParam(required = false, value = "sort") String sort,
            @ApiParam(name="order", value = "排序方式")
            @RequestParam(required = false, value = "order") String order,
            @RequestParam(required = false, defaultValue = "", value = "tableCode") String tableCode,
            @RequestParam(required = false, value = "tableName") String tableName) {
        Map<String,Object> param = new HashMap<String, Object>();
        if(StringUtils.isNotBlank(sort)){
            param.put("sort", StringUtils.humpToLine(sort));
        }else{
            param.put("sort","table_id");
        }
        if(StringUtils.isNotBlank(order)){
            param.put("order",order);
        }else{
            param.put("order","desc");
        }
        if(StringUtils.isNotBlank(tableCode)){
            param.put("tableCode",tableCode);
        }
        if(StringUtils.isNotBlank(tableName)){
            param.put("tableName",tableName);
        }
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
    @RequestMapping(value = "/listTableCellForSubject",method = RequestMethod.POST)
    @ResponseBody
    @EnableExportTable
    @LogAround("请求列表数据")
    public Object listTableCellForSubject(
            @RequestParam(required = false, value = "tableId") int tableId) {
        List<Map<String,Object>> data = tableService.queryTableCell(tableId);
        for(int i=0;i<data.size();i++){
            data.get(i).put("isShow","1");
            data.get(i).put("columnOrder",i+1);
        }
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
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {

        Map<String,Object> param = new HashMap<String, Object>();

        if(StringUtils.isNotBlank(search)){
            param.put("tableName",search);
        }
        if(StringUtils.isNotBlank(sort)){
            param.put("sort", StringUtils.humpToLine(sort));
        }else{
            param.put("sort","table_name");
        }
        if(StringUtils.isNotBlank(order)){
            param.put("order",order);
        }else{
            param.put("order","desc");
        }
        //物理分页
        PageHelper.offsetPage(offset, limit);
        List data = tableService.queryDbTable(param);
        PageInfo page = new PageInfo(data);

        Map<String, Object> result = new HashMap<>();
        result.put("rows", page.getList());
        result.put("total", page.getTotal());
        return new NdpResult(NdpResultConstant.SUCCESS,result);
    }
    @ApiOperation(value = "库表选择字段列表数据")
    @RequestMapping(value = "/listDbTableColumns",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("列表数据")
    public Object listDbTableColumns(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "20", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order,
            @RequestParam(required = true, defaultValue = "", value = "tableName") String tableName,
            @RequestParam(required = false, value = "selectColumns") String selectColumns) {
//
        if(StringUtils.isNotBlank(sort)){
            sort= StringUtils.humpToLine(sort);
        }else{
            sort="table_name";
        }
        if(StringUtils.isNotBlank(order)){

        }else{
            order="desc";
        }
        StringBuffer sql = new StringBuffer();
        sql.append(" select COLUMN_NAME from information_schema.COLUMNS where 1=1 ");
        sql.append(" and table_schema ='").append("lambo").append("'");
        if(StringUtils.isNotBlank(tableName)){
           sql.append(" and table_name ='").append(tableName).append("'");
        }
        if(StringUtils.isNotBlank(search)){
            sql.append(" and column_name like '%").append(search).append("%'");
        }
        String columns="";
        Boolean isCon=false;
        if(selectColumns==null || "".equals(selectColumns) || "[]".equals(selectColumns)){
            sql.append("and COLUMN_NAME  in('").append("')");

        }else{
            columns=selectColumns.replace("[","").replace("\"","").replace("]","");
            isCon=true;
        }
        if(isCon){
            sql.append("and COLUMN_NAME  in(");
            String[] columnsArry= columns.split(",");
            int len = columnsArry.length;
            for (int i = 0; i < len; i++) {
                sql.append("'").append(columnsArry[i]).append("'");
                if (i < len - 1) {
                    sql.append(",");
                }
            }
            sql.append(")");
        }
        sql.append("order by ").append(sort).append(" ").append(order);
        System.out.println("sql="+sql.toString());
        Map param = new HashMap();
        param.put("sql",sql.toString());
        PageHelper.offsetPage(offset,limit);
        List data = tableService.queryDbTableColumns(param);
        PageInfo page = new PageInfo(data);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", page.getList());
        result.put("total", page.getTotal());
        return new NdpResult(NdpResultConstant.SUCCESS,result);
    }
    @ApiOperation(value = "新增库表数据")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @Transactional
    public Object create(
            @RequestParam(required = true, value = "tableCode") String tableCode,
            @RequestParam(required = false, value = "tableName") String tableName,
            @RequestParam(required = false, value = "tableDesc") String tableDesc,
            @RequestParam(required = false, value = "TableCellss" ) String tableCellss) {
            return tableService.create(tableCode,tableName,tableDesc,tableCellss);
    }

    @ApiOperation(value = "更新库表")
    @RequestMapping(value = "/update/{tableId}", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public Object updateUser(
            @PathVariable("tableId") int tableId,
            @RequestParam(required = true, value = "tableCode") String tableCode,
            @RequestParam(required = true, value = "tableName") String tableName,
            @RequestParam(required = false, value = "tableDesc") String tableDesc,
            @RequestParam(required = false, value = "TableCellss" ) String tableCellss
    ){
        return tableService.update(tableId,tableCode,tableName,tableDesc,tableCellss);
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
    @Transactional
    public Object deleteTable(
            @PathVariable("tableId") int tableId
    ){
        return tableService.deleteTable(tableId);
    }
    @ApiOperation(value = "根据ID查询库表")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("id") int id) {
     return tableService.get(id);
    }
    @ApiOperation(value = "根据库表查询列")
    @RequestMapping(value = "/getColumn/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object getC(@PathVariable("id") String id) {
        return tableService.queryTableColumns(id);
    }
}