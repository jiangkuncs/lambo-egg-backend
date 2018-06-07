package com.lambo.rest.client.service.impl;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.rest.client.dao.api.RestClientSettingParamMapper;
import com.lambo.rest.client.model.RestSettingParam;
import com.lambo.rest.client.model.RestSettingParamExample;
import com.lambo.rest.client.service.api.RestClientSettingParamService;
import org.springframework.stereotype.Service;

/**
 * RestSettingService实现
 *
*/
@Service
@BaseService
public class RestClientSettingParamServiceImpl extends BaseServiceImpl<RestClientSettingParamMapper, RestSettingParam, RestSettingParamExample> implements RestClientSettingParamService {

}