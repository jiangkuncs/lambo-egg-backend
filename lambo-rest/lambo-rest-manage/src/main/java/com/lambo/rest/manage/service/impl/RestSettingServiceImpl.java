package com.lambo.rest.manage.service.impl;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.rest.manage.dao.api.RestSettingMapper;
import com.lambo.rest.manage.dao.api.RestSettingParamsMapper;
import com.lambo.rest.manage.model.RestSetting;
import com.lambo.rest.manage.model.RestSettingExample;
import com.lambo.rest.manage.model.RestSettingParams;
import com.lambo.rest.manage.model.RestSettingParamsExample;
import com.lambo.rest.manage.service.api.RestSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RestSettingService实现
 *
*/
@Service
@BaseService
public class RestSettingServiceImpl extends BaseServiceImpl<RestSettingMapper, RestSetting, RestSettingExample> implements RestSettingService {


    @Autowired
    RestSettingMapper restSettingMapper;

    @Autowired
    RestSettingParamsMapper restSettingParamsMapper;

    @Override
    @Transactional
    public Integer insert( RestSetting restSetting, List<RestSettingParams> paramsList){

        //REST_SETTING
        int count = restSettingMapper.insert(restSetting);

        //REST_SETTING_PARAMS
        if(null!=paramsList && paramsList.size()>0){
            for(int i=0;i<paramsList.size();i++){
                restSettingParamsMapper.insert((RestSettingParams)paramsList.get(i));
            }
        }

        return count;
    }

    @Override
    @Transactional
    public Integer update(RestSetting restSetting,List<RestSettingParams> paramsList){

        int count =restSettingMapper.updateByPrimaryKeyWithBLOBs(restSetting);

        //REST_SETTING_PARAMS
        //先删除后插入
        RestSettingParamsExample restSettingParamsExample =  new RestSettingParamsExample();
        restSettingParamsExample.createCriteria().andRestIdEqualTo(restSetting.getRestId());
        restSettingParamsMapper.deleteByExample(restSettingParamsExample);

        if(null!=paramsList && paramsList.size()>0){
            for(int i=0;i<paramsList.size();i++){
                restSettingParamsMapper.insert((RestSettingParams)paramsList.get(i));
            }
        }

        return count;
    }

    @Override
    public Object query(String restId){
        Map dataMap = new HashMap();

        if(null!=restId || !"".endsWith(restId)) {

            //REST_SETTING
            RestSetting restSetting = restSettingMapper.selectByPrimaryKey(restId);
            if (null != restSetting) {
                dataMap.put("restSetting", restSetting);
            }

            //REST_SETTING_PARAMS
            RestSettingParamsExample restSettingParamsExample = new RestSettingParamsExample();
            restSettingParamsExample.createCriteria().andRestIdEqualTo(restId);
            List<RestSettingParams> restSettingParamsList = restSettingParamsMapper.selectByExample(restSettingParamsExample);
            if (null != restSettingParamsList) {
                dataMap.put("restSettingParamsList", restSettingParamsList);
            }
        }

        return dataMap;
    }
}