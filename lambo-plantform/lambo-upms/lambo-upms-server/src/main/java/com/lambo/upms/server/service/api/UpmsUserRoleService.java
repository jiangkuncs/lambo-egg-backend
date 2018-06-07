package com.lambo.upms.server.service.api;

import com.lambo.common.base.BaseService;
import com.lambo.upms.server.dao.model.*;

/**
* UpmsUserRoleService接口
* Created by lambo on 2017/3/20.
*/
public interface UpmsUserRoleService extends BaseService<UpmsUserRole, UpmsUserRoleExample> {

    /**
     * 用户角色
     * @param roleIds 角色ids
     * @param id 用户id
     * @return
     */
    int role(String[] roleIds, int id);

}