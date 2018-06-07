package com.lambo.upms.server.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.lambo.common.base.BaseJunit4Test;
import com.lambo.upms.server.dao.model.UpmsUserPermission;
import com.lambo.upms.server.dao.model.UpmsUserPermissionExample;
import com.lambo.upms.server.service.api.UpmsUserPermissionService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class UpmsUserPermissionServiceImplTest extends BaseJunit4Test {

    @Autowired
    UpmsUserPermissionService upmsUserPermissionService;

    /**
     * 验证增加权限
     * @throws Exception
     */
    @Test
    public void addPermission() throws Exception {
        //删除权限
        UpmsUserPermissionExample upmsUserPermissionExample = new UpmsUserPermissionExample();
        upmsUserPermissionExample.createCriteria().andUserIdEqualTo(1);
        upmsUserPermissionService.deleteByExample(upmsUserPermissionExample);

        //增加权限
        String testStr = "[{\"checked\":true,\"id\":4,\"type\":1},{\"checked\":true,\"id\":6,\"type\":1},{\"checked\":true,\"id\":30,\"type\":1},{\"checked\":true,\"id\":50,\"type\":1}]";
        JSONArray datas = JSONArray.parseArray(testStr);
        upmsUserPermissionService.permission(datas,1);

        //验证
        List<UpmsUserPermission> list = upmsUserPermissionService.selectByExample(upmsUserPermissionExample);
        Assert.assertTrue(list.size() == 4);

    }

    @Test
    public void deletePermission() throws Exception {
        //删除前的权限
        UpmsUserPermissionExample upmsUserPermissionExample = new UpmsUserPermissionExample();
        upmsUserPermissionExample.createCriteria().andUserIdEqualTo(2);
        List<UpmsUserPermission> list1 = upmsUserPermissionService.selectByExample(upmsUserPermissionExample);

        //删除权限
        String testStr = "[{\"checked\":false,\"id\":24,\"type\":-1},{\"checked\":false,\"id\":26,\"type\":-1},{\"checked\":false,\"id\":27,\"type\":-1},{\"checked\":false,\"id\":29,\"type\":-1}]";
        JSONArray datas = JSONArray.parseArray(testStr);
        upmsUserPermissionService.permission(datas,2);

        //删除后的权限
        List<UpmsUserPermission> list2 = upmsUserPermissionService.selectByExample(upmsUserPermissionExample);

        //验证
        Assert.assertTrue(list1.size() - list2.size() == 4);
    }
}