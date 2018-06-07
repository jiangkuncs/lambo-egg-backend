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
    public Object getResult(RestSetting restSetting, Map paramMap, Boolean mock);

    public Object excutor1(String sql, String operation_type, String datasource);

    public Object excutor2(String sql, String operation_type, String datasource);


}