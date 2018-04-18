package com.lambo.ndp.controller;

import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.ndp.constant.NdpResult;
import com.lambo.ndp.constant.NdpResultConstant;
import com.lambo.ndp.service.api.HomepageService;
import com.lambo.ndp.service.api.OverviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import util.DateTool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 行业概览controller
 * Created by linqy on 2018/3/08.
 */
@Controller
@Api(value = "行业概览", description = "行业概览")
@RequestMapping("/main/overview")
public class OverviewController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(OverviewController.class);

    @Autowired
    private OverviewService overviewService;

    @Autowired
    private HomepageService homepageService;

    @ApiOperation(value = "行业分类概览")
    @RequestMapping(value = "/getCategoryOverview",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("行业分类概览")
    public Object getCategoryOverview() {
        Map<String,Object> param = new HashMap<>();
        param.put("TODAY", DateTool.getToday());
        param.put("YESTERDAY", DateTool.getBeforeOrNextDay(DateTool.getToday(), -1));

        Map<String,Object> result = overviewService.getCategoryOverview(param);

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

    @ApiOperation(value = "行业概览头数据")
    @RequestMapping(value = "/getMeasures",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("请求行业概览头数据")
    public Object getMeasures() {
        Map<String,Object> param = new HashMap<>();
        param.put("TODAY", DateTool.getToday());
        param.put("YESTERDAY", DateTool.getBeforeOrNextDay(DateTool.getToday(), -1));

        Map<String,Object> result = overviewService.getMeasures(param);

        return new NdpResult(NdpResultConstant.SUCCESS,result);
    }

    @ApiOperation(value = "行业概览-销售进度")
    @RequestMapping(value = "/getSaleProcess",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("请求行业概览销售进度")
    public Object getSaleProcess() {
        Map<String,Object> param = new HashMap<>();
        param.put("TODAY", DateTool.getToday());
        param.put("YESTERDAY", DateTool.getBeforeOrNextDay(DateTool.getToday(), -1));

        Map<String,Object> result = overviewService.getSaleProcess(param);

        return new NdpResult(NdpResultConstant.SUCCESS,result);
    }

    @ApiOperation(value = "行业概览-销量同期")
    @RequestMapping(value = "/getSaleSameRate",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("请求行业概览销量同期")
    public Object getSaleSameRate() {
        Map<String,Object> param = new HashMap<>();
        param.put("TODAY", DateTool.getToday());
        param.put("YESTERDAY", DateTool.getBeforeOrNextDay(DateTool.getToday(), -1));

        Map<String,Object> result = overviewService.getSaleSameRate(param);

        return new NdpResult(NdpResultConstant.SUCCESS,result);
    }

    @ApiOperation(value = "行业概览-单箱值")
    @RequestMapping(value = "/getBoxPrice",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("请求行业概览单箱值")
    public Object getBoxPrice() {
        Map<String,Object> param = new HashMap<>();
        param.put("TODAY", DateTool.getToday());
        param.put("YESTERDAY", DateTool.getBeforeOrNextDay(DateTool.getToday(), -1));

        Map<String,Object> result = overviewService.getBoxPrice(param);

        return new NdpResult(NdpResultConstant.SUCCESS,result);
    }

    @ApiOperation(value = "行业概览-经营户数")
    @RequestMapping(value = "/getCustNum",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("请求行业概览经营户数")
    public Object getCustNum() {
        Map<String,Object> param = new HashMap<>();
        param.put("TODAY", DateTool.getToday());
        param.put("YESTERDAY", DateTool.getBeforeOrNextDay(DateTool.getToday(), -1));

        Map<String,Object> result = overviewService.getCustNum(param);

        return new NdpResult(NdpResultConstant.SUCCESS,result);
    }

    @ApiOperation(value = "行业概览-大户占比")
    @RequestMapping(value = "/getBigCust",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("请求行业概览大户占比")
    public Object getBigCust() {
        Map<String,Object> param = new HashMap<>();
        param.put("TODAY", DateTool.getToday());
        param.put("YESTERDAY", DateTool.getBeforeOrNextDay(DateTool.getToday(), -1));

        Map<String,Object> result = overviewService.getBigCust(param);

        return new NdpResult(NdpResultConstant.SUCCESS,result);
    }

    @ApiOperation(value = "行业概览-自律互助小组")
    @RequestMapping(value = "/getSelfregGroup",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("请求行业概览自律互助小组")
    public Object getSelfregGroup() {
        Map<String,Object> param = new HashMap<>();
        param.put("TODAY", DateTool.getToday());
        param.put("YESTERDAY", DateTool.getBeforeOrNextDay(DateTool.getToday(), -1));

        Map<String,Object> result = overviewService.getSelfregGroup(param);

        return new NdpResult(NdpResultConstant.SUCCESS,result);
    }

    @ApiOperation(value = "行业概览-现代终端建设")
    @RequestMapping(value = "/getTerminalBulid",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("请求行业概览现代终端建设")
    public Object getTerminalBulid() {
        Map<String,Object> param = new HashMap<>();
        param.put("TODAY", DateTool.getToday());
        param.put("YESTERDAY", DateTool.getBeforeOrNextDay(DateTool.getToday(), -1));

        Map<String,Object> result = overviewService.getTerminalBulid(param);

        return new NdpResult(NdpResultConstant.SUCCESS,result);
    }
}