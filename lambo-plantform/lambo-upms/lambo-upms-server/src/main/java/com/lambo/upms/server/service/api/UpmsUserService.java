package com.lambo.upms.server.service.api;

import com.lambo.common.base.BaseService;
import com.lambo.upms.server.dao.model.*;

/**
* UpmsUserService接口
* Created by lambo on 2017/3/20.
*/
public interface UpmsUserService extends BaseService<UpmsUser, UpmsUserExample> {

    UpmsUser createUser(UpmsUser upmsUser);

}