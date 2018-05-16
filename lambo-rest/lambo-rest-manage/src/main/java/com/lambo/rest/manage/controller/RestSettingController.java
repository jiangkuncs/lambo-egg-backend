package com.lambo.rest.manage.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.rest.manage.model.RestSetting;
import com.lambo.rest.manage.model.RestSettingParams;
import com.lambo.rest.manage.service.api.RestSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Api(value = "rest服务管理", description = "rest服务管理")
@RequestMapping("/manage/rest/setting")
public class RestSettingController {
    private static Logger logger = LoggerFactory.getLogger(RestSettingController.class);

    @Autowired
    RestSettingService restSettingService;

    @ApiOperation(value = "新增rest服务")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    @LogAround("新增rest服务")
    public Object insert(@RequestParam(required = true, value = "restId") String restId,
                         @RequestParam(required = true, value = "restName") String restName,
                         @RequestParam(required = false, value = "datasource") String datasource,
                         @RequestParam(required = false, value = "operationType") String operationType,
                         @RequestParam(required = false, value = "url") String url,
                         @RequestParam(required = false, value = "note") String note,
                         @RequestParam(required = false, value = "restSql") String restSql,
                         @RequestParam(required = false, value = "mockData") String mockData,
                         @RequestParam(required = false, value = "settingParams") String settingParams) {

        //REST_SETTING
        RestSetting restSetting = new RestSetting();

        restSetting.setRestId(restId);
        restSetting.setRestName(restName);
        restSetting.setDatasource(datasource);
        restSetting.setOperationType(operationType);
        restSetting.setUrl(url);
        restSetting.setNote(note);
        restSetting.setRestSql(restSql);
        restSetting.setMockData(mockData);

        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateTime = format0.format(date);

        restSetting.setCreateTime(dateTime);
        restSetting.setUpdateTime(dateTime);

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        restSetting.setCreateUser(username);

        //REST_SETTING_PARAMS
        List<RestSettingParams> paramsList = getParamsList(restId,settingParams);


        int count = restSettingService.insert(restSetting,paramsList);

        return new BaseResult(BaseResultConstant.SUCCESS,count);
    }

    @ApiOperation(value = "更新rest服务")
    @RequestMapping(value = "/update/{restId}",method = RequestMethod.POST)
    @ResponseBody
    @LogAround("更新rest服务")
    public Object update(@PathVariable("restId") String restId,
                         @RequestParam(required = false, value = "restName") String restName,
                         @RequestParam(required = false, value = "datasource") String datasource,
                         @RequestParam(required = false, value = "operationType") String operationType,
                         @RequestParam(required = false, value = "url") String url,
                         @RequestParam(required = false, value = "note") String note,
                         @RequestParam(required = false, value = "restSql") String restSql,
                         @RequestParam(required = false, value = "mockData") String mockData,
                         @RequestParam(required = false, value = "createTime") String createTime,
                         @RequestParam(required = false, value = "settingParams") String settingParams) {

        //REST_SETTING
        RestSetting restSetting = new RestSetting();

        restSetting.setRestId(restId);
        restSetting.setRestName(restName);
        restSetting.setDatasource(datasource);
        restSetting.setOperationType(operationType);
        restSetting.setUrl(url);
        restSetting.setNote(note);
        restSetting.setRestSql(restSql);
        restSetting.setMockData(mockData);
        restSetting.setCreateTime(createTime);

        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateTime = format0.format(date);
        restSetting.setUpdateTime(dateTime);

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        restSetting.setCreateUser(username);

        //REST_SETTING_PARAMS
        List<RestSettingParams> paramsList = getParamsList(restId,settingParams);

        int count = restSettingService.update(restSetting,paramsList);

        return new BaseResult(BaseResultConstant.SUCCESS,restId);
    }

    @ApiOperation(value = "查询rest服务")
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("查询rest服务")
    public Object get(
            @RequestParam(required = true, value = "restId") String restId) {

        return  new BaseResult(BaseResultConstant.SUCCESS,restSettingService.query(restId));
    }

    private List getParamsList(String restId,String settingParams){
        List paramsList = new ArrayList();

        if(null!=settingParams && !("").equals(settingParams)){
            JSONArray paramsJson = JSONArray.parseArray(settingParams);
            if(paramsJson.size()>0){
                for(int i=0;i<paramsJson.size();i++){
                    JSONObject json = paramsJson.getJSONObject(i);

                    RestSettingParams restSettingParams = new RestSettingParams();
                    restSettingParams.setRestId(restId);
                    restSettingParams.setParamKey((String)json.get("paramKey"));
                    restSettingParams.setParamName((String)json.get("paramName"));
                    restSettingParams.setNecessary((String)json.get("necessary"));
                    restSettingParams.setDefaultValue((String)json.get("defaultValue"));
                    restSettingParams.setOrderSeq(i+1);

                    paramsList.add(restSettingParams);
                }
            }
        }

        return paramsList;
    }
}
