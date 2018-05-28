package com.lambo.rest.manage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lambo.common.annotation.EnableExportTable;
import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.common.utils.lang.StringUtils;
import com.lambo.rest.manage.model.RestDatasource;
import com.lambo.rest.manage.model.RestDatasourceExample;
import com.lambo.rest.manage.model.RestSetting;
import com.lambo.rest.manage.model.RestSettingParams;
import com.lambo.rest.manage.service.api.RestDatasourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lambo.common.base.BaseController.RESULT_ROWS;
import static com.lambo.common.base.BaseController.RESULT_TOTLAL;

@Controller
@Api(value = "rest数据源管理", description = "rest数据源管理")
@RequestMapping("/manage/rest/datasource")
public class RestDatasourceController {

    @Autowired
    RestDatasourceService restDatasourceService;

    @ApiOperation(value = "查询数据源")
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("查询rest数据源")
    public Object query(
            @RequestParam(required = false, value = "dsId") String dsId) {

        RestDatasourceExample restDatasourceExample =  new RestDatasourceExample();
        if(null!=dsId && !("").endsWith(dsId)) {
            restDatasourceExample.createCriteria().andDsIdEqualTo(dsId);
        }

        RestDatasource restDatasource = restDatasourceService.selectFirstByExample(restDatasourceExample);

        return new BaseResult(BaseResultConstant.SUCCESS,restDatasource);
    }

    @ApiOperation(value = "获取所有数据源")
    @RequestMapping(value = "/queryAll",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("查询rest所有数据源")
    public Object queryAll() {

        RestDatasourceExample restDatasourceExample =  new RestDatasourceExample();
        List<RestDatasource> dsList = restDatasourceService.selectByExample(restDatasourceExample);

        return new BaseResult(BaseResultConstant.SUCCESS,dsList);
    }

    @ApiOperation(value = "数据源列表数据")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {

        RestDatasourceExample restDatasourceExample = new RestDatasourceExample();
        if(StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)){
            restDatasourceExample.setOrderByClause(StringUtils.humpToLine(sort) + " " + order);
        }

        if(StringUtils.isNotBlank(search)){
            restDatasourceExample.or().andDsIdLike('%'+search+'%');
            restDatasourceExample.or().andDsNameLike('%'+search+'%');
        }

        //物理分页
        PageHelper.offsetPage(offset, limit);
        List<RestDatasource> data = restDatasourceService.selectByExample(restDatasourceExample);
        PageInfo page = new PageInfo(data);

        Map<String, Object> result = new HashMap<>();
        result.put(RESULT_ROWS, page.getList());
        result.put(RESULT_TOTLAL, page.getTotal());
        return new BaseResult(BaseResultConstant.SUCCESS,result);
    }

    @ApiOperation(value = "数据源列表数据导出")
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

        BaseResult result = (BaseResult)list(offset,limit,search,sort,order);
        return (Map)result.getData();
    }

    @ApiOperation(value = "删除数据源")
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = restDatasourceService.deleteByPrimaryKeys(ids);
        return new BaseResult(BaseResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "新增数据源")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    @LogAround("新增数据源")
    public Object insert(@RequestParam(required = true, value = "dsId") String dsId,
                         @RequestParam(required = true, value = "dsName") String dsName,
                         @RequestParam(required = false, value = "dsType") String dsType,
                         @RequestParam(required = false, value = "dsIp") String dsIp,
                         @RequestParam(required = false, value = "dsPort") int dsPort,
                         @RequestParam(required = false, value = "dsDatabase") String dsDatabase,
                         @RequestParam(required = false, value = "dsUser") String dsUser,
                         @RequestParam(required = false, value = "dsPassword") String dsPassword,
                         @RequestParam(required = false, value = "note") String note) {

        RestDatasource restDatasource = new RestDatasource();

        restDatasource.setDsId(dsId);
        restDatasource.setDsName(dsName);
        restDatasource.setDsType(dsType);
        restDatasource.setDsId(dsIp);
        restDatasource.setDsPort(dsPort);
        restDatasource.setDsDatabase(dsDatabase);
        restDatasource.setDsUser(dsUser);
        restDatasource.setDsPassword(dsPassword);
        restDatasource.setNote(note);


        int count = restDatasourceService.insert(restDatasource);

        return new BaseResult(BaseResultConstant.SUCCESS,count);
    }

    @ApiOperation(value = "更新数据源")
    @RequestMapping(value = "/update/{restId}",method = RequestMethod.POST)
    @ResponseBody
    @LogAround("更新数据源")
    public Object update(@PathVariable("dsId") String dsId,
                         @RequestParam(required = true, value = "dsName") String dsName,
                         @RequestParam(required = false, value = "dsType") String dsType,
                         @RequestParam(required = false, value = "dsIp") String dsIp,
                         @RequestParam(required = false, value = "dsPort") int dsPort,
                         @RequestParam(required = false, value = "dsDatabase") String dsDatabase,
                         @RequestParam(required = false, value = "dsUser") String dsUser,
                         @RequestParam(required = false, value = "dsPassword") String dsPassword,
                         @RequestParam(required = false, value = "note") String note) {

        RestDatasource restDatasource = new RestDatasource();

        restDatasource.setDsId(dsId);
        restDatasource.setDsName(dsName);
        restDatasource.setDsType(dsType);
        restDatasource.setDsId(dsIp);
        restDatasource.setDsPort(dsPort);
        restDatasource.setDsDatabase(dsDatabase);
        restDatasource.setDsUser(dsUser);
        restDatasource.setDsPassword(dsPassword);
        restDatasource.setNote(note);

        int count = restDatasourceService.updateByPrimaryKey(restDatasource);

        return new BaseResult(BaseResultConstant.SUCCESS,count);
    }
}
