package com.lambo.rest.manage.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.common.utils.idgen.IdGenerate;
import com.lambo.rest.manage.model.RestSetting;
import com.lambo.rest.manage.model.RestSettingParams;
import com.lambo.rest.manage.model.RestStru;
import com.lambo.rest.manage.service.api.RestMamageService;
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
@RequestMapping("/manage/rest/manage")
public class RestManageController {
    private static Logger logger = LoggerFactory.getLogger(RestManageController.class);

    @Autowired
    RestMamageService restMamageService;

    @ApiOperation(value = "新增rest服务")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    @LogAround("新增rest服务")
    public Object insert(@RequestParam(required = true, value = "struName") String struName,
                         @RequestParam(required = true, value = "struUrl") String struUrl,
                         @RequestParam(required = true, value = "isLeaf") String isLeaf,
                         @RequestParam(required = false, value = "parentId") String parentId,
                         @RequestParam(required = false, value = "isUse") String isUse,
                         @RequestParam(required = false, value = "orderSeq") Integer orderSeq,
                         @RequestParam(required = false, value = "datasource") String datasource,
                         @RequestParam(required = false, value = "operationType") String operationType,
                         @RequestParam(required = false, value = "url") String url,
                         @RequestParam(required = false, value = "note") String note,
                         @RequestParam(required = false, value = "restSql") String restSql,
                         @RequestParam(required = false, value = "mockData") String mockData,
                         @RequestParam(required = false, value = "settingParams") String settingParams) {

        RestStru restStru = new RestStru();
        RestSetting restSetting = new RestSetting();
        List<RestSettingParams> paramsList = new ArrayList();

        String uuid = IdGenerate.uuid();

        //REST_STRU
        restStru.setStruId(uuid);
        restStru.setStruName(struName);
        restStru.setStruUrl(struUrl);
        restStru.setIsLeaf(isLeaf);
        restStru.setParentId(parentId);
        restStru.setIsUse(isUse);
        restStru.setOrderSeq(orderSeq);

        if(("1").equals(isLeaf)){
            restStru.setRestId(uuid);

            //REST_SETTING
            restSetting.setRestId(uuid);
            restSetting.setRestName(struName);
            restSetting.setDatasource(datasource);
            restSetting.setOperationType(operationType);
            restSetting.setUrl(url);
            restSetting.setNote(note);
            restSetting.setRestSql(restSql);
            restSetting.setMockData(mockData);

            SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String dateTime = format0.format(date);

            logger.info("dateTime="+dateTime);
            restSetting.setCreateTime(dateTime);
            restSetting.setUpdateTime(dateTime);

            Subject subject = SecurityUtils.getSubject();
            String username = (String) subject.getPrincipal();
            restSetting.setCreateUser(username);

            //REST_SETTING_PARAMS
            if(null!=settingParams && !("").equals(settingParams)){
                JSONArray paramsJson = JSONArray.parseArray(settingParams);
                if(paramsJson.size()>0){
                    for(int i=0;i<paramsJson.size();i++){
                        JSONObject json = paramsJson.getJSONObject(i);

                        RestSettingParams restSettingParams = new RestSettingParams();
                        restSettingParams.setRestId(uuid);
                        restSettingParams.setParamKey((String)json.get("paramKey"));
                        restSettingParams.setParamName((String)json.get("paramName"));
                        restSettingParams.setNecessary((String)json.get("necessary"));
                        restSettingParams.setDefaultValue((String)json.get("defaultValue"));
                        restSettingParams.setOrderSeq(i+1);

                        paramsList.add(restSettingParams);
                    }
                }
            }
        }else{
            restStru.setRestId("");
        }

        int count = restMamageService.insert(restStru,restSetting,paramsList);

        return new BaseResult(BaseResultConstant.SUCCESS,uuid);
    }

    @ApiOperation(value = "更新rest服务")
    @RequestMapping(value = "/update/{struId}",method = RequestMethod.POST)
    @ResponseBody
    @LogAround("更新rest服务")
    public Object update(@PathVariable("struId") String struId,
                         @RequestParam(required = true, value = "struName") String struName,
                         @RequestParam(required = true, value = "struUrl") String struUrl,
                         @RequestParam(required = true, value = "isLeaf") String isLeaf,
                         @RequestParam(required = false, value = "parentId") String parentId,
                         @RequestParam(required = false, value = "isUse") String isUse,
                         @RequestParam(required = false, value = "orderSeq") Integer orderSeq,
                         @RequestParam(required = false, value = "restId") String restId,
                         @RequestParam(required = false, value = "restName") String restName,
                         @RequestParam(required = false, value = "datasource") String datasource,
                         @RequestParam(required = false, value = "operationType") String operationType,
                         @RequestParam(required = false, value = "url") String url,
                         @RequestParam(required = false, value = "note") String note,
                         @RequestParam(required = false, value = "restSql") String restSql,
                         @RequestParam(required = false, value = "mockData") String mockData,
                         @RequestParam(required = false, value = "createTime") String createTime,
                         @RequestParam(required = false, value = "settingParams") String settingParams) {

        logger.info("createTime="+createTime);


        RestStru restStru = new RestStru();
        RestSetting restSetting = new RestSetting();
        List<RestSettingParams> paramsList = new ArrayList();

        //REST_STRU
        restStru.setStruId(struId);
        restStru.setStruName(struName);
        restStru.setStruUrl(struUrl);
        restStru.setIsLeaf(isLeaf);
        restStru.setRestId(restId);
        restStru.setParentId(parentId);
        restStru.setIsUse(isUse);
        restStru.setOrderSeq(orderSeq);

        if(("1").equals(isLeaf)){
            //REST_SETTING
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
        }

        int count = restMamageService.update(restStru,restSetting,paramsList);

        return count;
    }

    @ApiOperation(value = "查询rest服务")
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    @ResponseBody
    @LogAround("查询rest服务")
    public Object get(
            @RequestParam(required = true, value = "struId") String struId) {

        return  new BaseResult(BaseResultConstant.SUCCESS,restMamageService.query(struId));
    }

}
