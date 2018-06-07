package com.lambo.rest.manage.service.impl;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.common.utils.idgen.IdGenerate;
import com.lambo.rest.manage.dao.api.RestSettingLogMapper;
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

import javax.security.sasl.SaslServer;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    RestSettingLogMapper restSettingLogMapper;
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
        //更新之前先备份一下
        String restId=restSetting.getRestId();
        Map restMap= (Map) query(restId);
        RestSetting restSettingOld= (RestSetting) restMap.get("restSetting");

        String uuid = IdGenerate.uuid();
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateTime = format0.format(date);

        Map  restSetLog=new HashMap();
        restSetLog.put("logId",uuid);
        restSetLog.put("restId",restSettingOld.getRestId());
        restSetLog.put("url",restSettingOld.getUrl());
        restSetLog.put("dataSource",restSettingOld.getDatasource());
        restSetLog.put("restSql",restSettingOld.getRestSql());
        restSetLog.put("mockData",restSettingOld.getMockData());
        restSetLog.put("note",restSettingOld.getNote());
        restSetLog.put("time",dateTime);
        restSetLog.put("ip",null);
        restSetLog.put("userAgent",null);
        int count1=restSettingLogMapper.insertRestSetLog(restSetLog);
        //参数
        List<RestSettingParams> restSettingParamsList= (List<RestSettingParams>) restMap.get("restSettingParamsList");
        String uuid1 = IdGenerate.uuid();
        for(RestSettingParams parm : restSettingParamsList){
            Map  restSetParamsLog=new HashMap();
            restSetParamsLog.put("logId",uuid1);
            restSetParamsLog.put("restId",parm.getRestId());
            restSetParamsLog.put("paramKey",parm.getParamKey());
            restSetParamsLog.put("paramName",parm.getParamName());
            restSetParamsLog.put("necessary",parm.getNecessary());
            restSetParamsLog.put("defaultValue",parm.getDefaultValue());
            restSetParamsLog.put("time",dateTime);
            restSetParamsLog.put("ip",null);
            restSetParamsLog.put("userAgent",null);
            int count2=restSettingLogMapper.insertRestSetParamsLog(restSetParamsLog);
        }

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