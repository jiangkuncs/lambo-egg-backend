package com.lambo.rest.manage.service.impl;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.common.utils.idgen.IdGenerate;
import com.lambo.rest.manage.dao.api.RestSettingLogMapper;
import com.lambo.rest.manage.dao.api.RestSettingParamsLogMapper;
import com.lambo.rest.manage.model.*;
import com.lambo.rest.manage.service.api.RestSettingLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RestSettingService实现
 *
 */
@Service
@BaseService
public class RestSettingLogServiceImpl extends BaseServiceImpl<RestSettingLogMapper, RestSettingLog, RestSettingLogExample> implements RestSettingLogService {
    private static Logger logger = LoggerFactory.getLogger(RestSettingLogServiceImpl.class);

    @Autowired
    RestSettingLogMapper restSettingLogMapper;

    @Autowired
    RestSettingParamsLogMapper restSettingParamsLogMapper;

    @Override
    @Transactional
    public Integer insert(RestSetting restSetting, List<RestSettingParams> paramsList){

        //记录日志
        String logId = IdGenerate.uuid();
        Map<String,String> hostMap = getHost();
        String hostIp = hostMap.get("hostIp")==null?"":hostMap.get("hostIp");
        String hostName = hostMap.get("hostName")==null?"":hostMap.get("hostName");
        String hostMac = hostMap.get("hostMac")==null?"":hostMap.get("hostMac");

        //REST_SETTING_LOG
        RestSettingLog restSettingLog = new RestSettingLog();
        restSettingLog.setLogId(logId);
        restSettingLog.setRestId(restSetting.getRestId());
        restSettingLog.setUrl(restSetting.getUrl());
        restSettingLog.setDatasource(restSetting.getDatasource());
        restSettingLog.setRestSql(restSetting.getRestSql());
        restSettingLog.setMockData(restSetting.getMockData());
        restSettingLog.setNote(restSetting.getNote());
        restSettingLog.setTime(restSetting.getUpdateTime());
        restSettingLog.setUser(restSetting.getCreateUser());
        restSettingLog.setHostIp(hostIp);
        restSettingLog.setHostName(hostName);
        restSettingLog.setHostMac(hostMac);
        int count = restSettingLogMapper.insert(restSettingLog);

        //REST_SETTING_PARAMS_LOG
        if(null!=paramsList && paramsList.size()>0){
            for(int i=0;i<paramsList.size();i++){
                RestSettingParams restSettingParams = (RestSettingParams)paramsList.get(i);
                RestSettingParamsLog restSettingParamsLog = new RestSettingParamsLog();

                restSettingParamsLog.setLogId(logId);
                restSettingParamsLog.setRestId(restSettingParams.getRestId());
                restSettingParamsLog.setParamKey(restSettingParams.getParamKey());
                restSettingParamsLog.setParamName(restSettingParams.getParamName());
                restSettingParamsLog.setNecessary(restSettingParams.getNecessary());
                restSettingParamsLog.setDefaultValue(restSettingParams.getDefaultValue());
                restSettingParamsLog.setNote(restSettingParams.getNote());
                restSettingParamsLog.setOrderSeq(restSettingParams.getOrderSeq());

                restSettingParamsLogMapper.insert(restSettingParamsLog);
            }
        }



        return count;
    }

    private Map getHost(){
        Map<String,String> map = new HashMap<String,String>();

        try{
            InetAddress addr = InetAddress.getLocalHost();
            String hostIp=addr.getHostAddress().toString(); //获取本机ip
            String hostName=addr.getHostName().toString(); //获取本机计算机名称
            String hostMac = getLocalMac(addr);

            map.put("hostIp",hostIp);
            map.put("hostName",hostName);
            map.put("hostMac",hostMac);

        }catch(Exception e){
            logger.info("获取用户的信息失败，"+e);
        }

        return map;
    }

    private String getLocalMac(InetAddress ia){
        StringBuffer sb = new StringBuffer("");

        //获取网卡，获取地址
        try{
            byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
            for(int i=0; i<mac.length; i++) {
                if(i!=0) {
                    sb.append("-");
                }
                //字节转换为整数
                int temp = mac[i]&0xff;
                String str = Integer.toHexString(temp);
                System.out.println("每8位:"+str);
                if(str.length()==1) {
                    sb.append("0"+str);
                }else {
                    sb.append(str);
                }
            }
        }catch(SocketException e){
            logger.info("获取用户的mac地址失败，"+e);
        }

        return sb.toString().toUpperCase();
    }
}
