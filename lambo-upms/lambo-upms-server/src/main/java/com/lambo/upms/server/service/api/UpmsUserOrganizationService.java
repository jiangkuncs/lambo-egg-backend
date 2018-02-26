package com.lambo.upms.server.service.api;

import com.lambo.common.base.BaseService;
import com.lambo.upms.server.dao.model.*;

/**
* UpmsUserOrganizationService接口
* Created by lambo on 2017/3/20.
*/
public interface UpmsUserOrganizationService extends BaseService<UpmsUserOrganization, UpmsUserOrganizationExample> {

    /**
     * 用户组织
     * @param organizationIds 组织ids
     * @param id 用户id
     * @return
     */
    int organization(String[] organizationIds, int id);

}