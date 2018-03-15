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
import com.lambo.ndp.model.*;
import com.lambo.ndp.service.api.CateGoryService;
import com.lambo.ndp.service.api.DictService;
import com.lambo.ndp.service.api.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日志controller
 * Created by lambo on 2017/3/14.
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
            @RequestParam(required = false, defaultValue = "", value = "search") String search) {
        return ((NdpResult)list(offset,limit,search)).data;

    }
    @ApiOperation(value = "数据专题列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("请求列表数据")
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "SubjectName") String SubjectName) {
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("SubjectName",SubjectName);
        //物理分页
        PageHelper.offsetPage(offset, limit);
        List<Map<String,Object>> data = subjectService.querySubject(param);
        PageInfo page = new PageInfo(data);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", page.getList());
        result.put("total", page.getTotal());
        return new NdpResult(NdpResultConstant.SUCCESS,result);
    }

}