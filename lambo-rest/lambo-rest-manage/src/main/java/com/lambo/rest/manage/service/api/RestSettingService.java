package com.lambo.rest.manage.service.api;

import com.lambo.common.base.BaseService;
import com.lambo.rest.manage.model.RestSetting;
import com.lambo.rest.manage.model.RestSettingExample;
import com.lambo.rest.manage.model.RestSettingParams;
import com.lambo.rest.manage.model.RestStru;

import java.util.List;

/**
 * RestSettingService
 */
public interface RestSettingService extends BaseService<RestSetting, RestSettingExample> {


    public Integer insertRest(RestStru restStru,RestSetting restSetting,List<RestSettingParams> paramsList);

}