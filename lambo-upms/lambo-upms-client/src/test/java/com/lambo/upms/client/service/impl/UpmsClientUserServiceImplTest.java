package com.lambo.upms.client.service.impl;

import com.lambo.common.base.BaseJunit4Test;
import com.lambo.upms.client.dao.model.UpmsUser;
import com.lambo.upms.client.dao.model.UpmsUserExample;
import com.lambo.upms.client.service.api.UpmsClientUserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UpmsClientUserServiceImplTest extends BaseJunit4Test {

    @Autowired
    UpmsClientUserService upmsClientUserService;
    @Test
    public void createUser() throws Exception {
        UpmsUser upmsUser =  new UpmsUser();
        upmsUser.setUsername("测试1");
        upmsUser.setPassword("12345");
        upmsClientUserService.createUser(upmsUser);

        UpmsUserExample upmsUserExample =  new UpmsUserExample();
        upmsUserExample.createCriteria().andUsernameEqualTo("测试1");
        List<UpmsUser> list = upmsClientUserService.selectByExample(upmsUserExample);
        Assert.assertTrue(list.size() == 1);
    }

}