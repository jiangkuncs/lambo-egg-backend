package com.lambo.rest.manage.service.impl;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.rest.manage.dao.api.RestSettingMapper;
import com.lambo.rest.manage.model.RestSetting;
import com.lambo.rest.manage.model.RestSettingExample;
import com.lambo.rest.manage.service.api.RestSettingService;
import org.springframework.stereotype.Service;

/**
 * RestSettingService实现
 *
*/
@Service
@BaseService
public class RestSettingServiceImpl extends BaseServiceImpl<RestSettingMapper, RestSetting, RestSettingExample> implements RestSettingService {

}