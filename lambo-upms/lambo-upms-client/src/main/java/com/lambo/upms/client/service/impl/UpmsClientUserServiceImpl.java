package com.lambo.upms.client.service.impl;

import com.lambo.upms.client.dao.model.UpmsUserExample;
import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.upms.client.dao.api.UpmsClientUserMapper;
import com.lambo.upms.client.dao.model.UpmsUser;
import com.lambo.upms.client.service.api.UpmsClientUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsUserService实现
* Created by lambo on 2017/3/20.
*/
@Service
@Transactional
@BaseService
public class UpmsClientUserServiceImpl extends BaseServiceImpl<UpmsClientUserMapper, UpmsUser, UpmsUserExample> implements UpmsClientUserService {

    private static Logger logger = LoggerFactory.getLogger(UpmsClientUserServiceImpl.class);

    @Autowired
    UpmsClientUserMapper upmsUserMapper;

    @Override
    public UpmsUser createUser(UpmsUser upmsUser) {
        UpmsUserExample upmsUserExample = new UpmsUserExample();
        upmsUserExample.createCriteria()
                .andUsernameEqualTo(upmsUser.getUsername());
        long count = upmsUserMapper.countByExample(upmsUserExample);
        if (count > 0) {
            return null;
        }
        upmsUserMapper.insert(upmsUser);
        return upmsUser;
    }

}