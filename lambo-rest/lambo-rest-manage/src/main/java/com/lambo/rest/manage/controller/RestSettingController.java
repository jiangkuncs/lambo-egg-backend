package com.lambo.rest.manage.controller;


import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.common.utils.idgen.IdGenerate;
import com.lambo.rest.manage.model.RestSetting;
import com.lambo.rest.manage.model.RestSettingExample;
import com.lambo.rest.manage.service.api.RestSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@Controller
@Api(value = "rest服务管理", description = "rest服务管理")
@RequestMapping("/manage/rest/setting")
public class RestSettingController extends BaseController {

    @Autowired
    RestSettingService restSettingService;

    @ApiOperation(value = "新增rest服务")
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("新增rest服务")
    public Object create() {

        RestSetting restSetting = new RestSetting();
        restSetting.setRestId(IdGenerate.uuid());
        restSetting.setRestName("测试1");
        restSetting.setUrl("测试2");
        restSetting.setCreateTime(new Date());
        restSetting.setUpdateTime(new Date());
        restSetting.setDatasource("测试3");
        restSetting.setRestSql("aaaaaaaaaaaaaaaaaa");
        restSetting.setMockData("xxx");
        restSetting.setNote("note");
        restSetting.setCreateUser("admin");

        int count = restSettingService.insert(restSetting);

        return count;
    }

    @ApiOperation(value = "更新rest服务")
    @RequestMapping(value = "/update/{restId}",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("更新rest服务")
    public Object update(@PathVariable("restId") String restId) {

        RestSetting restSetting = new RestSetting();
        restSetting.setRestId(restId);
        restSetting.setRestName("测试1");
        restSetting.setUrl("测试2");
        restSetting.setUpdateTime(new Date());
        restSetting.setDatasource("测试3");
        restSetting.setRestSql("aaaaaaaaaaaaaaaaaa");
        restSetting.setMockData("xxx");
        restSetting.setNote("note");
        restSetting.setCreateUser("admin123");

        int count = restSettingService.updateByPrimaryKey(restSetting);

        return count;
    }

    @ApiOperation(value = "查询rest服务")
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("查询rest服务")
    public Object get(
            @RequestParam(required = false, value = "url") String url) {

        RestSettingExample restSettingExample =  new RestSettingExample();
        restSettingExample.createCriteria().andUrlLike("%"+url+"%");

        return restSettingService.selectByExample(restSettingExample);
    }
}
