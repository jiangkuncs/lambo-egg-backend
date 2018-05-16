package com.lambo.rest.manage.controller;

import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.rest.manage.model.RestDatasource;
import com.lambo.rest.manage.model.RestDatasourceExample;
import com.lambo.rest.manage.service.api.RestDatasourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
}
