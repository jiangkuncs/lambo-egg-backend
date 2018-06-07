package com.lambo.rest.client.service.impl;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.rest.client.dao.api.RestClientDatasourceMapper;
import com.lambo.rest.client.dao.api.RestClientSettingMapper;
import com.lambo.rest.client.model.RestDatasource;
import com.lambo.rest.client.model.RestDatasourceExample;
import com.lambo.rest.client.model.RestSetting;
import com.lambo.rest.client.model.RestSettingExample;
import com.lambo.rest.client.service.api.RestClientDatasourceService;
import com.lambo.rest.client.service.api.RestClientSettingService;
import org.springframework.stereotype.Service;

/**
 * RestSettingService实现
 *
*/
@Service
@BaseService
public class RestClientDatasourceServiceImpl extends BaseServiceImpl<RestClientDatasourceMapper, RestDatasource, RestDatasourceExample> implements RestClientDatasourceService {

}