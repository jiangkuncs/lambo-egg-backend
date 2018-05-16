package com.lambo.rest.manage.service.api;

import com.lambo.common.base.BaseService;
import com.lambo.rest.manage.model.RestSetting;
import com.lambo.rest.manage.model.RestSettingExample;
import com.lambo.rest.manage.model.RestSettingParams;

import java.util.List;

/**
 * RestSettingService
 */
public interface RestSettingService extends BaseService<RestSetting, RestSettingExample> {

    public Integer insert(RestSetting restSetting,List<RestSettingParams> paramsList);

    public Integer update(RestSetting restSetting,List<RestSettingParams> paramsList);

    public Object query(String struId);

}