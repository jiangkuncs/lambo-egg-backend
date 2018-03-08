package com.lambo.ndp.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lambo.common.annotation.EnableExportTable;
import com.lambo.common.annotation.LogAround;
import com.lambo.ndp.service.DemoUserService;
import com.lambo.ndp.service.FrontendService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户controller
 * Created by wangjie on 2017/11/14.
 */
@Controller()
public class frontendController {
    private static Logger logger = LoggerFactory.getLogger(frontendController.class);

    @Autowired
    private FrontendService frontendService;
    /**
     * 分类列表
     * @return
     */
    @ApiOperation(value = "分类列表")
    @RequestMapping(value = "/dataCategory/getCategoryList")
    @ResponseBody
    @LogAround(value="分类列表")
    public List getCategoryList()  {
        List list = frontendService.getCategoryList(null);
        return list;
    }
    /**
     * 专题列表
     * @return
     */
    @ApiOperation(value = "专题列表")
    @RequestMapping(value = "/dataSubject/getSubjectList")
    @ResponseBody
    @LogAround(value="专题列表")
    public List getSubjectList(
            @ApiParam(name="categoryId", value = "分类ID")
            @RequestParam(required = false, defaultValue = "", value = "categoryId") String categoryId)  {
        Map param = new HashMap();
        if(!categoryId.isEmpty()){
            param.put("category_id",categoryId);
        }
        List list = frontendService.getSubjectList(param);
        return list;
    }
    /**
     * 专题详情
     * @return
     */
    @ApiOperation(value = "专题详情")
    @RequestMapping(value = "/dataSubject/getSubjectInfo")
    @ResponseBody
    @LogAround(value="专题详情")
    public List getSubjectInfo(
            @ApiParam(name="subjectId", value = "专题ID")
            @RequestParam(required = false, defaultValue = "", value = "subjectId") String subjectId)  {
        Map param = new HashMap();
        if(!subjectId.isEmpty()){
            param.put("subject_id",subjectId);
        }else{
            return new ArrayList();
        }
        return frontendService.getSubjectInfo(param);
    }
    /**
     * 查询数据
     * @return
     */
    @ApiOperation(value = "查询数据")
    @RequestMapping(value = "/dataSubject/getTableData",method = RequestMethod.GET)
    @ResponseBody
    @LogAround(value="查询数据")
    public Map getTableData(
            @ApiParam(name="offset", value = "起始行号")
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @ApiParam(name="limit", value = "条数")
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @ApiParam(required = true, name="subjectId", value = "专题ID")
            @RequestParam(required = true, defaultValue = "", value = "subjectId") String subjectId,
            @ApiParam(name="params", value = "参数")
            @RequestParam(required = false, defaultValue = "", value = "params") String params)  {
        Map param = new HashMap();
        param.put("offset",offset);
        param.put("limit",limit);
        if(!subjectId.isEmpty()){
            param.put("subjectId",subjectId);
        }else{
            Map result = new HashMap();
            result.put("rows",new ArrayList());
            result.put("total",0);
            return result;
        }
        if(!params.isEmpty()){
            param.put("params",params);
        }
        Map result = frontendService.getTableData(param);
        return result;
    }

    @ApiOperation(value = "列表数据导出")
    @RequestMapping(value = "/dataSubject/getTableData",method = RequestMethod.POST)
    @ResponseBody
    @EnableExportTable
    @LogAround("列表数据导出")
    public Map exportList(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @ApiParam(required = true, name="subjectId", value = "专题ID")
            @RequestParam(required = true, defaultValue = "", value = "subjectId") String subjectId,
            @ApiParam(name="params", value = "参数")
            @RequestParam(required = false, defaultValue = "", value = "params") String params){

        Map result = getTableData(offset,limit,subjectId,params);
        return result;
    }

    /**
     * 查询纬度
     * @return
     */
    @ApiOperation(value = "查询纬度")
    @RequestMapping(value = "/dataSubject/getDimensionData")
    @ResponseBody
    @LogAround(value="查询纬度")
    public List getDimensionData(
            @ApiParam(name="dimensionId", value = "纬度ID")
            @RequestParam(required = false, defaultValue = "", value = "dimensionId") String dimensionId)  {
        Map param = new HashMap();
        if(!dimensionId.isEmpty()){
            param.put("dimensionId",dimensionId);
        }
        List list = frontendService.getDimensionData(param);
        return list;
    }
}
