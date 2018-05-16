package com.lambo.rest.manage.service.api;

import com.lambo.common.base.BaseService;
import com.lambo.rest.manage.model.*;

import java.util.List;

/**
 * RestMamageService
 */
public interface RestMamageService extends BaseService<RestStru,RestStruExample> {

    public Integer insert(RestStru restStru,RestSetting restSetting,List<RestSettingParams> paramsList);

    public Integer update(RestStru restStru,RestSetting restSetting,List<RestSettingParams> paramsList);

    public Integer delete(String struId);

    public Object query(String struId);

}
