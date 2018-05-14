package com.lambo.rest.client.controller;

import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.rest.client.model.RestSetting;
import com.lambo.rest.client.model.RestSettingExample;
import com.lambo.rest.client.model.RestSettingParam;
import com.lambo.rest.client.model.RestSettingParamExample;
import com.lambo.rest.client.service.api.RestClientService;
import com.lambo.rest.client.service.api.RestSettingParamService;
import com.lambo.rest.client.service.api.RestSettingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RestClientController
 * @Descirption TODO
 * @Author sunzhen
 * @Date 2018/5/11 11:37
 **/
@Controller
public class RestClientController {

    private static final String URL_PREFIX = "/rest/service";

    @Autowired
    RestSettingService restSettingService;
    @Autowired
    RestSettingParamService restSettingParamService;
    @Autowired
    RestClientService restClientService;


    @ApiOperation(value = "通用数据服务")
    @RequestMapping(value = URL_PREFIX+"/**",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object query(HttpServletRequest request) {

        RestSetting restSetting = getRestSettingByUrl(request.getRequestURI().toString());
        String restId = restSetting.getRestId();
        List<RestSettingParam> restSettingParamList = getRestSettingParamByRestId(restId);
        Map paramMap = new HashMap();
        for(RestSettingParam restSettingParam : restSettingParamList){
            String paramKey = restSettingParam.getParamKey();
            String paramValue = request.getParameter(paramKey);
            boolean necessary = restSettingParam.getNecessary().equals(1);
            if(paramValue == null){
                if(necessary){
                    throw new RuntimeException("服务"+restId+"参数"+paramKey+"不允许为空");
                }else{
                    paramValue = restSettingParam.getDefaultValue();
                }
            }
            paramMap.put(paramKey,paramValue);
        }
        boolean mock = request.getParameter("mock") != null && request.getParameter("mock").equals("true");

        Object object = restClientService.getResult(restSetting,paramMap,mock);

        return new BaseResult(BaseResultConstant.SUCCESS,object);
    }

    /**
     * 通过url获取服务设置
     * @param url
     * @return
     */
    private RestSetting getRestSettingByUrl(String url){
        if(url == null){
            throw new RuntimeException("服务url不能为空");
        }
        url = url.replace(URL_PREFIX,"");
        RestSettingExample restSettingExample = new RestSettingExample();
        restSettingExample.createCriteria().andUrlEqualTo(url.trim());
        List<RestSetting> list = restSettingService.selectByExampleWithBLOBs(restSettingExample);
        if(list == null || list.size() == 0){
            throw new RuntimeException("服务"+url+"不存在,请检查数据");
        }
        if(list.size() > 1){
            throw new RuntimeException("服务"+url+"重复,请检查数据");
        }
        return list.get(0);
    }

    /**
     * 根据restId获取服务参数
     * @param restId
     * @return
     */
    private List<RestSettingParam> getRestSettingParamByRestId(String restId){
        RestSettingParamExample restSettingParamExample = new RestSettingParamExample();
        restSettingParamExample.createCriteria().andRestIdEqualTo(restId);
        List<RestSettingParam> list = restSettingParamService.selectByExample(restSettingParamExample);
        return list;
    }
}
