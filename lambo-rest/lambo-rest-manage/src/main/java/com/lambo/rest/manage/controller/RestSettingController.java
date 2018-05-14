package com.lambo.rest.manage.controller;


import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseController;
import com.lambo.common.utils.idgen.IdGenerate;
import com.lambo.rest.manage.model.RestSetting;
import com.lambo.rest.manage.model.RestSettingExample;
import com.lambo.rest.manage.model.RestSettingParams;
import com.lambo.rest.manage.model.RestStru;
import com.lambo.rest.manage.service.api.RestSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@Api(value = "rest服务管理", description = "rest服务管理")
@RequestMapping("/manage/rest/setting")
public class RestSettingController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(RestSettingController.class);

    @Autowired
    RestSettingService restSettingService;

    @ApiOperation(value = "新增rest服务")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    @LogAround("新增rest服务")
    public Object insert(@RequestParam(required = true, value = "struName") String struName,
                         @RequestParam(required = true, value = "isLeaf") String isLeaf,
                         @RequestParam(required = false, value = "parentId") String parentId,
                         @RequestParam(required = false, value = "isUse") String isUse) {

        RestStru restStru = new RestStru();
        RestSetting restSetting = new RestSetting();
        List<RestSettingParams> paramsList = new ArrayList();

        String uuid = IdGenerate.uuid();

        //REST_STRU
        restStru.setStruId(uuid);
        restStru.setStruName(struName);
        restStru.setIsLeaf(isLeaf);
        restStru.setParentId(parentId);
        restStru.setIsUse(isUse);

        logger.debug("restStru="+restStru);
        logger.debug("restSetting="+restSetting);
        logger.debug("paramsList="+paramsList);

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
