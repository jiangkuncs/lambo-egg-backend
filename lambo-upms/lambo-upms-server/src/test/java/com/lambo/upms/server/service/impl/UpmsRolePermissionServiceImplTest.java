package com.lambo.upms.server.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.lambo.common.base.BaseJunit4Test;
import com.lambo.upms.server.dao.model.UpmsRolePermission;
import com.lambo.upms.server.dao.model.UpmsRolePermissionExample;
import com.lambo.upms.server.service.api.UpmsRolePermissionService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UpmsRolePermissionServiceImplTest extends BaseJunit4Test {

    @Autowired
    UpmsRolePermissionService upmsRolePermissionService;


    @Test
    public void rolePermissionAdd() throws Exception {
        //删除角色1的权限
        UpmsRolePermissionExample upmsRolePermissionExample =  new UpmsRolePermissionExample();
        upmsRolePermissionExample.createCriteria().andRoleIdEqualTo(1);
        upmsRolePermissionService.deleteByExample(upmsRolePermissionExample);
        //增加权限
        String testStr = "[{\"checked\":true,\"id\":4},{\"checked\":true,\"id\":6},{\"checked\":true,\"id\":30},{\"checked\":true,\"id\":50}]";
        JSONArray datas = JSONArray.parseArray(testStr);
        upmsRolePermissionService.rolePermission(datas,1);
        //验证
        List<UpmsRolePermission> list = upmsRolePermissionService.selectByExample(upmsRolePermissionExample);
        Assert.assertTrue(list.size() == 4);
    }

    @Test
    public void rolePermissionDelete() throws Exception {
        UpmsRolePermissionExample upmsRolePermissionExample =  new UpmsRolePermissionExample();
        upmsRolePermissionExample.createCriteria().andRoleIdEqualTo(1);
        //当前角色1的权限
        List<UpmsRolePermission> list1 = upmsRolePermissionService.selectByExample(upmsRolePermissionExample);
        //删除权限
        String testStr = "[{\"checked\":false,\"id\":4},{\"checked\":false,\"id\":6}]";
        JSONArray datas = JSONArray.parseArray(testStr);
        upmsRolePermissionService.rolePermission(datas,1);
        //删除后权限后，角色1的权限
        List<UpmsRolePermission> list2 = upmsRolePermissionService.selectByExample(upmsRolePermissionExample);
        //验证
        Assert.assertTrue(list1.size() - list2.size() == 2);
    }

}