package com.lambo.upms.server.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.lambo.common.base.BaseJunit4Test;
import com.lambo.upms.server.service.api.UpmsPermissionService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UpmsPermissionServiceImplTest extends BaseJunit4Test {

    @Autowired
    UpmsPermissionService upmsPermissionService;

    @Test
    public void getTreeByRoleId() throws Exception {
        JSONArray tree = upmsPermissionService.getTreeByRoleId(1);
        Assert.assertTrue(tree.size() > 1);
    }

    @Test
    public void getTreeByUserId() throws Exception {
        JSONArray tree = upmsPermissionService.getTreeByUserId(1,new Byte("1"));
        Assert.assertTrue(tree.size() > 1);
    }

}