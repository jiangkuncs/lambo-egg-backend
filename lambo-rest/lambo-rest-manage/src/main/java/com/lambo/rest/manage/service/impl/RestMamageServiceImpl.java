package com.lambo.rest.manage.service.impl;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.rest.manage.dao.api.RestSettingMapper;
import com.lambo.rest.manage.dao.api.RestSettingParamsMapper;
import com.lambo.rest.manage.dao.api.RestStruMapper;
import com.lambo.rest.manage.model.*;
import com.lambo.rest.manage.service.api.RestMamageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RestService实现
 *
 */
@Service
@BaseService
public class RestMamageServiceImpl extends BaseServiceImpl<RestStruMapper, RestStru, RestStruExample> implements RestMamageService {

    @Autowired
    RestStruMapper restStruMapper;

    @Autowired
    RestSettingMapper restSettingMapper;

    @Autowired
    RestSettingParamsMapper restSettingParamsMapper;

    @Override
    @Transactional
    public Integer insert(RestStru restStru, RestSetting restSetting, List<RestSettingParams> paramsList){

        //REST_STRU
        int count = restStruMapper.insert(restStru);

        if("1".equals(restStru.getIsLeaf())){

            //REST_SETTING
            restSettingMapper.insert(restSetting);

            //REST_SETTING_PARAMS
            if(null!=paramsList && paramsList.size()>0){
                for(int i=0;i<paramsList.size();i++){
                    restSettingParamsMapper.insert((RestSettingParams)paramsList.get(i));
                }
            }
        }

        return count;
    }

    @Override
    public Object query(String struId){
        Map dataMap = new HashMap();

        if(null!=struId || !"".endsWith(struId)) {

            //REST_STRU
            RestStru restStru = restStruMapper.selectByPrimaryKey(struId);
            dataMap.put("restStru", restStru);

            if ("1".equals(restStru.getIsLeaf())) {
                //REST_SETTING
                RestSetting restSetting = restSettingMapper.selectByPrimaryKey(struId);
                if (null != restSetting) {
                    dataMap.put("restSetting", restSetting);
                }

                //REST_SETTING_PARAMS
                RestSettingParamsExample restSettingParamsExample = new RestSettingParamsExample();
                restSettingParamsExample.createCriteria().andRestIdEqualTo(struId);
                List<RestSettingParams> restSettingParamsList = restSettingParamsMapper.selectByExample(restSettingParamsExample);
                if (null != restSettingParamsList) {
                    dataMap.put("restSettingParamsList", restSettingParamsList);
                }
            }
        }

        return dataMap;
    }

}
