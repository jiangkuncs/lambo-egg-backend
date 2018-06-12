package com.lambo.rest.manage.service.api;

import com.lambo.common.base.BaseService;
import com.lambo.rest.manage.model.RestSetting;
import com.lambo.rest.manage.model.RestSettingLog;
import com.lambo.rest.manage.model.RestSettingLogExample;
import com.lambo.rest.manage.model.RestSettingParams;

import java.util.List;

/**
 * RestSettingService
 */
public interface RestSettingLogService extends BaseService<RestSettingLog, RestSettingLogExample> {

    public Integer insert(RestSetting restSetting, List<RestSettingParams> paramsList);

}
