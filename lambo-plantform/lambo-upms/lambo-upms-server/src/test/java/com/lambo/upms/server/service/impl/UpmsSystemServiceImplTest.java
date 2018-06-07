package com.lambo.upms.server.service.impl;

import com.lambo.common.base.BaseJunit4Test;
import com.lambo.upms.server.dao.model.UpmsSystem;
import com.lambo.upms.server.service.api.UpmsSystemService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.Assert;


public class UpmsSystemServiceImplTest extends BaseJunit4Test {

    @Autowired
    private UpmsSystemService upmsSystemService;

    @Test
    public void selectUpmsSystemByName() throws Exception {
        String name = "lambo-upms-server";
        UpmsSystem upmsSystem = upmsSystemService.selectUpmsSystemByName(name);
        Assert.assertTrue(upmsSystem != null);
    }

}