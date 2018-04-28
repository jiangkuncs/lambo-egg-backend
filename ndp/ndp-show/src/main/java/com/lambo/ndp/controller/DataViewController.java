package com.lambo.ndp.controller;

import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.ndp.constant.NdpResult;
import com.lambo.ndp.constant.NdpResultConstant;
import com.lambo.ndp.service.api.DataViewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 反馈信息controller
 * Created by SunJibing on 2018/4/18.
 */
@Controller
@Api(value = "数据词典服务", description = "数据词典服务")
@RequestMapping("/manage/dataView")
public class DataViewController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(DataViewController.class);
    @Autowired
    private DataViewService dataViewService;

    @ApiOperation(value = "获取数据词典")
    @RequestMapping(value = "/getConditionMap", method = RequestMethod.POST)
    @ResponseBody
    @LogAround("获取查询条件")
    public Object getConditionMap() {

        Map resultMap = dataViewService.getConditionMap();
        if (resultMap.isEmpty())
            return new NdpResult(NdpResultConstant.FAILED, null);
        else
            return new NdpResult(NdpResultConstant.SUCCESS, resultMap);
    }

    @ApiOperation(value = "获取查询数据")
    @RequestMapping(value = "/getSearchResult", method = RequestMethod.POST)
    @ResponseBody
    @LogAround("请求列表数据")
    public Object getSearchResult(
            @RequestParam(required = false, value = "activeOrder") String activeOrder,
            @RequestParam(required = false, value = "activeStars") String activeStars,
            @RequestParam(required = false, value = "activeTags") List activeTags,
            @RequestParam(required = false, value = "catograyId") String catograyId,
            @RequestParam(required = false, value = "organTypeId") String organTypeId,
            @RequestParam(required = false, value = "periodTypeId") String periodTypeId,
            @RequestParam(required = false, value = "pageNum") int pageNum,
            @RequestParam(required = false, value = "pageSize") int pageSize) {

        Map paramMap = new HashMap();
        paramMap.put("activeOrder",activeOrder);
        paramMap.put("activeStars",activeStars);
        paramMap.put("activeTags",activeTags);
        paramMap.put("catograyId",catograyId);
        paramMap.put("organTypeId",organTypeId);
        paramMap.put("periodTypeId",periodTypeId);




        //PageHelper.startPage(pageNum, pageSize);
        int startRow = (pageNum - 1) * pageSize;
        PageHelper.offsetPage(startRow, pageSize);
        paramMap.put("pageNum",pageNum);
        paramMap.put("pageSize",pageSize);
        List<Map<String, String>> resultList = dataViewService.getSearchResult(paramMap);
        //list可以直接用，也可以用下边的方法转成PageInfo对象，以获取记录总数等其他信息
        PageInfo page = new PageInfo(resultList);

        return new NdpResult(NdpResultConstant.SUCCESS, page);

    }

}