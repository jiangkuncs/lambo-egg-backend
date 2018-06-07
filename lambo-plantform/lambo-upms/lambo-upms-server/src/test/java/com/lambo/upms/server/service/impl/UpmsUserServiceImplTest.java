package com.lambo.upms.server.service.impl;

import com.lambo.common.base.BaseJunit4Test;
import com.lambo.upms.server.dao.model.UpmsUser;
import com.lambo.upms.server.dao.model.UpmsUserExample;
import com.lambo.upms.server.service.api.UpmsUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.junit.Assert;

@Transactional
public class UpmsUserServiceImplTest extends BaseJunit4Test {

    @Autowired
    UpmsUserService upmsUserService;
    @Test
    public void createUser() throws Exception {
        UpmsUser upmsUser =  new UpmsUser();
        upmsUser.setUsername("测试");
        upmsUser.setPassword("12345");
        upmsUserService.createUser(upmsUser);

        UpmsUserExample upmsUserExample =  new UpmsUserExample();
        upmsUserExample.createCriteria().andUsernameEqualTo("测试");
        List<UpmsUser> list = upmsUserService.selectByExample(upmsUserExample);
        Assert.assertTrue(list.size() == 1);
    }

}