package com.lambo.upms.server.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.upms.server.dao.api.*;
import com.lambo.upms.server.dao.model.*;
import com.lambo.upms.server.service.api.UpmsRolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* UpmsRolePermissionService实现
* Created by lambo on 2017/3/20.
*/
@Service
@Transactional
@BaseService
public class UpmsRolePermissionServiceImpl extends BaseServiceImpl<UpmsRolePermissionMapper, UpmsRolePermission, UpmsRolePermissionExample> implements UpmsRolePermissionService {

    private static Logger logger = LoggerFactory.getLogger(UpmsRolePermissionServiceImpl.class);

    @Autowired
    UpmsRolePermissionMapper upmsRolePermissionMapper;

    @Override
    public int rolePermission(JSONArray datas, int id) {
        List<Integer> deleteIds = new ArrayList<>();
        for (int i = 0; i < datas.size(); i ++) {
            JSONObject json = datas.getJSONObject(i);
            if (!json.getBoolean("checked")) {
                deleteIds.add(json.getIntValue("id"));
            } else {
                // 新增权限
                UpmsRolePermission upmsRolePermission = new UpmsRolePermission();
                upmsRolePermission.setRoleId(id);
                upmsRolePermission.setPermissionId(json.getIntValue("id"));
                upmsRolePermissionMapper.insertSelective(upmsRolePermission);
            }
        }
        // 删除权限
        if (deleteIds.size() > 0) {
            UpmsRolePermissionExample upmsRolePermissionExample = new UpmsRolePermissionExample();
            upmsRolePermissionExample.createCriteria()
                    .andPermissionIdIn(deleteIds)
                    .andRoleIdEqualTo(id);
            upmsRolePermissionMapper.deleteByExample(upmsRolePermissionExample);
        }
        return datas.size();
    }

}