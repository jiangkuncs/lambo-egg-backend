package com.lambo.rest.client.service.api;

import com.lambo.rest.client.model.RestSetting;

import java.util.Map;

/**
 * RestClientService
 */
public interface RestClientService {

    /**
     * 获取服务执行结果数据
     * @param restSetting
     * @param paramMap
     * @param mock
     * @return
     */
    Object getResult(RestSetting restSetting, Map paramMap, Boolean mock);


    Object excutor(String sql,String opration_type,String datasource);

}