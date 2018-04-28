package com.lambo.ndp.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lambo.common.annotation.EnableExportTable;
import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.common.utils.lang.StringUtils;
import com.lambo.ndp.constant.NdpResult;
import com.lambo.ndp.constant.NdpResultConstant;
import com.lambo.ndp.model.Dict;
import com.lambo.ndp.model.Subject;
import com.lambo.ndp.model.SubjectExample;
import com.lambo.ndp.service.api.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专题controller
 * Created by zxc on 2018/3/08.
 */
@Controller
@Api(value = "数据专题查询", description = "数据专题查询")
@RequestMapping("/manage/subjectData")
public class SubjectController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(SubjectController.class);

    @Autowired
    private SubjectService subjectService;
    @ApiOperation(value = "数据专题列表")
    @RequestMapping(value = "/list",method = RequestMethod.POST) //这是post方式用于导出
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
            @RequestParam(required = false, defaultValue = "", value = "search") String subjectName) {
        return ((NdpResult)list(offset,limit,sort,order,subjectName)).data;

    }
    @ApiOperation(value = "数据专题列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("请求列表数据")
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @ApiParam(name="sort", value = "排序字段")
            @RequestParam(required = false, value = "sort") String sort,
            @ApiParam(name="order", value = "排序方式")
            @RequestParam(required = false, value = "order") String order,
            @RequestParam(required = false, defaultValue = "", value = "subjectName") String subjectName) {
        Map<String,Object> param = new HashMap<String, Object>();
        SubjectExample subjectExample=new SubjectExample();
        if(StringUtils.isBlank(sort)){
            sort = "subject_id";
        }
        if(StringUtils.isBlank(order)){
            order = "desc";
        }
//        if(StringUtils.isNotBlank(sort)){
//            param.put("sort", StringUtil.humpToLine(sort));
//        }else{
//            param.put("sort","subject_id");
//        }
//        if(StringUtils.isNotBlank(order)){
//            param.put("order",order);
//        }else{
//            param.put("order","desc");
//        }
//        if(StringUtils.isNotBlank(subjectName)){
//            param.put("subjectName",subjectName);
//        }

        subjectExample.setOrderByClause(StringUtils.humpToLine(sort) + " " + order);
        if(StringUtils.isNotBlank(subjectName)){
            subjectExample.or().andSubjectNameLike("%"+subjectName+"%");
        }
        //物理分页
        PageHelper.offsetPage(offset, limit);
        List<Subject> data= subjectService.selectByExample(subjectExample);
       // List<Map<String,Object>> data = subjectService.querySubject(param);
        PageInfo page = new PageInfo(data);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", page.getList());
        result.put("total", page.getTotal());
        return new NdpResult(NdpResultConstant.SUCCESS,result);
    }
    @ApiOperation(value = "根据ID查询专题")
    @RequestMapping(value = "/get/{subjectId}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(@PathVariable("subjectId") int subjectId) {

        return subjectService.getSubjectById(subjectId);
    }
    @ApiOperation(value = "初始化数据")
    @RequestMapping(value = "/getInitData", method = RequestMethod.GET)
    @ResponseBody
    public Object initSubject() {

        return subjectService.initSubject();
    }
    @ApiOperation(value = "新增数据专题")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(
            @RequestParam(required = true, value = "categoryId") int categoryId,
            @RequestParam(required = false, value = "tableCode") String tableCode,
            @RequestParam(required = false, value = "tableId") int tableId,
            @RequestParam(required = false, value = "subjectDesc") String subjectDesc,
            @RequestParam(required = false, value = "subjectName") String subjectName,
            @RequestParam(required = false, value = "subjectType") String subjectType,
            @RequestParam(required = false, value = "subjectTime") String subjectTime,
            @RequestParam(required = false, value = "subjectOrgan") String subjectOrgan,
            @RequestParam(required = false, value = "subjectTag") String subjectTag,
            @RequestParam(required = false, value = "subjectColumns") String subjectColumns) {
        int con=(int)subjectService.insertSubject(categoryId,tableCode,tableId,subjectDesc,subjectName,subjectColumns,subjectType,subjectTime,subjectOrgan,subjectTag);
        return new NdpResult(NdpResultConstant.SUCCESS, con);

    }
    @ApiOperation(value = "新增数据专题")
    @ResponseBody
    @RequestMapping(value = "/update/{subjectId}", method = RequestMethod.POST)
    public Object update(
            @PathVariable("subjectId") int subjectId,
            @RequestParam(required = false, value = "categoryId") int categoryId,
            @RequestParam(required = false, value = "tableCode") String tableCode,
            @RequestParam(required = false, value = "tableId") int tableId,
            @RequestParam(required = false, value = "subjectDesc") String subjectDesc,
            @RequestParam(required = false, value = "subjectName") String subjectName,
            @RequestParam(required = false, value = "subjectType") String subjectType,
            @RequestParam(required = false, value = "subjectTime") String subjectTime,
            @RequestParam(required = false, value = "subjectOrgan") String subjectOrgan,
            @RequestParam(required = false, value = "subjectTag") String subjectTag,
            @RequestParam(required = false, value = "subjectColumns") String subjectColumns) {
        int con=(int)subjectService.updateSubject(subjectId,categoryId,tableCode,tableId,subjectDesc,subjectName,subjectColumns,subjectType,subjectTime,subjectOrgan,subjectTag);
        return new NdpResult(NdpResultConstant.SUCCESS, con);
    }
    @ApiOperation(value = "删除数据专题")
    @RequestMapping(value = "/delete/{subjectId}",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("请求列表数据")
    public Object delete(
            @PathVariable("subjectId") int subjectId) {
        return new NdpResult(NdpResultConstant.SUCCESS,subjectService.deleteSubjectBySubjectId(subjectId));
    }
}