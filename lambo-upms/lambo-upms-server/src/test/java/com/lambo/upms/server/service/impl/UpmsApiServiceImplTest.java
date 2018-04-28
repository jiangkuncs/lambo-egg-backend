package com.lambo.upms.server.service.impl;

import com.lambo.common.base.BaseJunit4Test;
import com.lambo.upms.server.dao.model.*;
import com.lambo.upms.server.service.api.UpmsApiService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;



public class UpmsApiServiceImplTest extends BaseJunit4Test {

    @Autowired
    UpmsApiService upmsApiService;

    @Test
    public void selectUpmsPermissionByUpmsUserId() throws Exception {
        List<UpmsPermission> list = upmsApiService.selectUpmsPermissionByUpmsUserId(1);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void selectUpmsPermissionByUpmsUserIdByCache() throws Exception {
        List<UpmsPermission> list = upmsApiService.selectUpmsPermissionByUpmsUserIdByCache(1);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void selectUpmsRoleByUpmsUserId() throws Exception {
        List<UpmsRole> list  = upmsApiService.selectUpmsRoleByUpmsUserId(1);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void selectUpmsRoleByUpmsUserIdByCache() throws Exception {
        List<UpmsRole> list  = upmsApiService.selectUpmsRoleByUpmsUserIdByCache(1);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void selectUpmsRolePermisstionByUpmsRoleId() throws Exception {
        List<UpmsRolePermission> list = upmsApiService.selectUpmsRolePermisstionByUpmsRoleId(1);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void selectUpmsUserPermissionByUpmsUserId() throws Exception {
        List<UpmsUserPermission> list = upmsApiService.selectUpmsUserPermissionByUpmsUserId(1);
        System.out.println(list);
    }

    @Test
    public void selectUpmsSystemByExample() throws Exception {
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        List list = upmsApiService.selectUpmsSystemByExample(upmsSystemExample);
    }

    @Test
    public void selectUpmsOrganizationByExample() throws Exception {
        UpmsOrganizationExample upmsOrganizationExample =  new UpmsOrganizationExample();
        upmsApiService.selectUpmsOrganizationByExample(upmsOrganizationExample);
    }

    @Test
    public void selectUpmsUserByUsername() throws Exception {

        UpmsUser upmsUser =  upmsApiService.selectUpmsUserByUsername("admin");
        Assert.assertTrue(upmsUser != null);
    }

    @Test
    public void insertUpmsLogSelective() throws Exception {
        UpmsLog upmsLog =  new UpmsLog();
        upmsLog.setDescription("测试");
        upmsLog.setParameter("测试");
        upmsLog.setBasePath("测试");
        int count = upmsApiService.insertUpmsLogSelective(upmsLog);
        Assert.assertTrue(count == 1);
    }

}