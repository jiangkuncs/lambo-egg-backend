package com.lambo.rest.manage.service.impl;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.rest.manage.dao.api.RestSettingMapper;
import com.lambo.rest.manage.model.RestSetting;
import com.lambo.rest.manage.model.RestSettingExample;
import com.lambo.rest.manage.model.RestSettingParams;
import com.lambo.rest.manage.model.RestStru;
import com.lambo.rest.manage.service.api.RestSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RestSettingService实现
 *
*/
@Service
@BaseService
public class RestSettingServiceImpl extends BaseServiceImpl<RestSettingMapper, RestSetting, RestSettingExample> implements RestSettingService {

    @Autowired
    RestSettingMapper restSettingMapper;

    @Override
    public Integer insertRest(RestStru restStru, RestSetting restSetting, List<RestSettingParams> paramsList){
        int count = restSettingMapper.insert(restSetting);

        return count;
    }

}