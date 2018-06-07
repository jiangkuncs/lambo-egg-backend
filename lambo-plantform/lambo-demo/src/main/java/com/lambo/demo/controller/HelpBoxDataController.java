package com.lambo.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lambo.common.annotation.EnableExportTable;
import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.common.utils.lang.StringUtils;
import com.lambo.demo.constant.DemoResult;
import com.lambo.demo.constant.DemoResultConstant;
import com.lambo.upms.client.dao.model.UpmsOrganization;
import com.lambo.upms.client.dao.model.UpmsOrganizationExample;
import com.lambo.upms.client.service.api.UpmsClientApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "帮助框示例数据", description = "帮助框示例数据")
@RequestMapping("/helpbox")
public class HelpBoxDataController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(HelpBoxDataController.class);

    @Autowired
    private UpmsClientApiService upmsClientApiService;

    @ApiOperation(value = "组织帮助框示例数据")
    @RequestMapping(value = "/organ/list",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("组织帮助框示例数据")
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {

        UpmsOrganizationExample upmsOrganizationExample = new UpmsOrganizationExample();

        if(StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)){
            upmsOrganizationExample.setOrderByClause(StringUtils.humpToLine(sort) + " " + order);
        }
        if(StringUtils.isNotBlank(search)){
            upmsOrganizationExample.createCriteria().andNameLike("%" + search + "%");
        }

        //物理分页
        PageHelper.offsetPage(offset, limit);
        List<UpmsOrganization> data = upmsClientApiService.selectUpmsOrganizationByExample(upmsOrganizationExample);
        PageInfo page = new PageInfo(data);

        Map<String, Object> result = new HashMap<>();
        result.put(RESULT_ROWS, page.getList());
        result.put(RESULT_TOTLAL, page.getTotal());
        return new DemoResult(DemoResultConstant.SUCCESS,result);
    }

    @ApiOperation(value = "组织帮助框示例数据导出")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    @EnableExportTable
    @LogAround("组织帮助框示例数据导出")
    public Map exportList(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {

        DemoResult result = (DemoResult)list(offset,limit,search,sort,order);
        return (Map)result.data;
    }
}