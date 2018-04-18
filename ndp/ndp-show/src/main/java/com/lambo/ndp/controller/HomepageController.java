package com.lambo.ndp.controller;

import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.ndp.constant.NdpResult;
import com.lambo.ndp.constant.NdpResultConstant;
import com.lambo.ndp.service.api.HomepageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import util.DateTool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页controller
 * Created by linqy on 2018/3/08.
 */
@Controller
@Api(value = "首页", description = "首页")
@RequestMapping("/main/homepage")
public class HomepageController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(HomepageController.class);

    @Autowired
    private HomepageService homepageService;

    @ApiOperation(value = "热门数据")
    @RequestMapping(value = "/getHotSubject",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("热门数据")
    public Object getHotSubject() {
        Map<String,Object> param = new HashMap<>();
        param.put("TODAY", DateTool.getToday());
        param.put("YESTERDAY", DateTool.getBeforeOrNextDay(DateTool.getToday(), -1));

        List<Map<String,Object>> result = homepageService.getHotSubject(param);

        return new NdpResult(NdpResultConstant.SUCCESS,result);
    }

    @ApiOperation(value = "最新数据")
    @RequestMapping(value = "/getNewSubject",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("最新数据")
    public Object getNewSubject() {
        Map<String,Object> param = new HashMap<>();
        param.put("TODAY", DateTool.getToday());
        param.put("YESTERDAY", DateTool.getBeforeOrNextDay(DateTool.getToday(), -1));

        List<Map<String,Object>> result = homepageService.getNewSubject(param);

        return new NdpResult(NdpResultConstant.SUCCESS,result);
    }

    @ApiOperation(value = "获取所有分类")
    @RequestMapping(value = "/getAllCategory",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("获取所有分类")
    public Object getAllCategory() {
        Map<String,Object> param = new HashMap<>();
        param.put("TODAY", DateTool.getToday());
        param.put("YESTERDAY", DateTool.getBeforeOrNextDay(DateTool.getToday(), -1));

        List<Map<String,Object>> result = homepageService.getAllCategory(param);

        return new NdpResult(NdpResultConstant.SUCCESS,result);
    }

    @ApiOperation(value = "数据概览")
    @RequestMapping(value = "/getDataStatistics",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("数据概览")
    public Object getDataStatistics() {
        Map<String,Object> param = new HashMap<>();
        param.put("TODAY", DateTool.getToday());
        param.put("YESTERDAY", DateTool.getBeforeOrNextDay(DateTool.getToday(), -1));

        Map<String,Object> result = homepageService.getDataStatistics(param);

        return new NdpResult(NdpResultConstant.SUCCESS,result);
    }
}