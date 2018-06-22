package com.lambo.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lambo.common.annotation.EnableExportTable;
import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.common.utils.lang.StringUtils;
import com.lambo.demo.constant.DemoResult;
import com.lambo.demo.constant.DemoResultConstant;
import com.lambo.demo.model.DemoLogExample;
import com.lambo.demo.model.DemoLog;
import com.lambo.demo.service.api.DemoLogService;
import com.lambo.demo.service.api.RpcTestService;
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
@Api(value = "分页表格示例数据", description = "分页表格示例数据")
@RequestMapping("/data")
public class DemoDataController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(DemoDataController.class);

    @Autowired
    private DemoLogService demoLogService;

    @Autowired
    private RpcTestService rpcTestService;

    @ApiOperation(value = "列表数据")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {

        DemoLogExample demoLogExample = new DemoLogExample();
        if(StringUtils.isBlank(sort)){
            sort = "startTime";
        }
        if(StringUtils.isBlank(order)){
            order = "desc";
        }
        demoLogExample.setOrderByClause(StringUtils.humpToLine(sort) + " " + order);

        if(StringUtils.isNotBlank(search)){
            demoLogExample.createCriteria().andDescriptionLike("%" + search + "%");
        }

        //物理分页
        PageHelper.offsetPage(offset, limit);
        List<DemoLog> data = demoLogService.selectByExample(demoLogExample);
        PageInfo page = new PageInfo(data);

        Map<String, Object> result = new HashMap<>();
        result.put(RESULT_ROWS, page.getList());
        result.put(RESULT_TOTLAL, page.getTotal());
        return new DemoResult(DemoResultConstant.SUCCESS,result);
    }

    @ApiOperation(value = "列表数据导出")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    @EnableExportTable
    @LogAround("列表数据导出")
    public Map exportList(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {

        DemoResult result = (DemoResult)list(offset,limit,search,sort,order);
        return (Map)result.data;
    }

    @ApiOperation(value = "删除数据")
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = demoLogService.deleteByPrimaryKeys(ids);
        return new DemoResult(DemoResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "测试服务")
    @RequestMapping(value = "/sayHello/{name}", method = RequestMethod.GET)
    @ResponseBody
    public Object sayHello(@PathVariable("name") String name) {
        return new DemoResult(DemoResultConstant.SUCCESS, rpcTestService.sayHello(name));
    }
}