package com.lambo.rest.client.controller;

import com.lambo.common.base.BaseController;
import com.lambo.common.base.BaseResult;
import com.lambo.common.base.BaseResultConstant;
import com.lambo.rest.client.model.RestSetting;
import com.lambo.rest.client.model.RestSettingExample;
import com.lambo.rest.client.model.RestSettingParam;
import com.lambo.rest.client.model.RestSettingParamExample;
import com.lambo.rest.client.service.api.RestClientService;
import com.lambo.rest.client.service.api.RestClientSettingParamService;
import com.lambo.rest.client.service.api.RestClientSettingService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
public class RestClientController extends BaseController {

    private static final String URL_PREFIX = "/rest/service";

    @Autowired
    RestClientSettingService restSettingService;
    @Autowired
    RestClientSettingParamService restSettingParamService;
    @Autowired
    RestClientService restClientService;


    @ApiOperation(value = "通用数据服务")
    @RequestMapping(value = URL_PREFIX+"/**",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object query(HttpServletRequest request) {

        RestSetting restSetting = getRestSettingByUrl(request.getRequestURI());
        String restId = restSetting.getRestId();
        List<RestSettingParam> restSettingParamList = getRestSettingParamByRestId(restId);
        Map<String,String> paramMap = new HashMap<>();
        for(RestSettingParam restSettingParam : restSettingParamList){
            String paramKey = restSettingParam.getParamKey();
            String paramValue = request.getParameter(paramKey);
            boolean necessary = restSettingParam.getNecessary().equals(1);
            if(StringUtils.isNotBlank(paramValue)){
                if(necessary){
                    throw new RuntimeException("服务"+restId+"参数"+paramKey+"不允许为空");
                }else{
                    String defaultValue = restSettingParam.getDefaultValue();
                    paramValue = StringUtils.isNotBlank(defaultValue) ? defaultValue : null;
                }
            }
            paramMap.put(paramKey,paramValue);
        }
        boolean mock = request.getParameter("mock") != null && "true".equals(request.getParameter("mock"));

        Object object = restClientService.getResult(restSetting,paramMap,mock);

        return new BaseResult(BaseResultConstant.SUCCESS,object);
    }



    @ApiOperation(value = "获取服务配置")
    @RequestMapping(value = "/client/rest/queryById",method = {RequestMethod.GET})
    @ResponseBody
    public Object queryById(@RequestParam(required = false, value = "restId") String restId) {

        RestSetting restSetting = getRestSettingByRestId(restId);
        List<RestSettingParam> paramList = getRestSettingParamByRestId(restId);

        Map map = new HashMap();
        map.put("restSetting",restSetting);
        map.put("paramList",paramList);
        return new BaseResult(BaseResultConstant.SUCCESS,map);

    }

    @ApiOperation(value = "获取服务配置")
    @RequestMapping(value = "/client/rest/queryByUrl",method = {RequestMethod.GET})
    @ResponseBody
    public Object queryByUrl(@RequestParam(required = false, value = "url") String url) {

        RestSetting restSetting = getRestSettingByUrl(url);
        List<RestSettingParam> paramList = getRestSettingParamByRestId(restSetting.getRestId());

        Map map = new HashMap();
        map.put("restSetting",restSetting);
        map.put("paramList",paramList);
        return new BaseResult(BaseResultConstant.SUCCESS,map);

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
        url = url.replaceAll(".*" + URL_PREFIX,"");
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
        return list == null ? new ArrayList<RestSettingParam>() : list;
    }

    private RestSetting getRestSettingByRestId(String restId){
        RestSettingExample restSettingExample = new RestSettingExample();
        restSettingExample.createCriteria().andRestIdEqualTo(restId);
        List<RestSetting> list =  restSettingService.selectByExampleWithBLOBs(restSettingExample);
        if(list == null){
            throw new RuntimeException("查询不到restId="+restId+"的服务");
        }
        return list.get(0);
    }


}
