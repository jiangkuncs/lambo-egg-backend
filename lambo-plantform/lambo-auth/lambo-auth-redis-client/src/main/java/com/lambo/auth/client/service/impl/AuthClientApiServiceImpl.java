package com.lambo.auth.client.service.impl;

import com.lambo.auth.client.dao.api.AuthClientApiMapper;
import com.lambo.auth.client.dao.api.AuthClientUserMapper;
import com.lambo.auth.client.dao.model.UpmsPermission;
import com.lambo.auth.client.dao.model.UpmsRole;
import com.lambo.auth.client.dao.model.UpmsUser;
import com.lambo.auth.client.dao.model.UpmsUserExample;
import com.lambo.auth.client.service.api.AuthClientApiService;
import com.lambo.common.utils.io.PropertiesFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * UpmsApiService实现
 * Created by lambo on 2016/01/19.
 */
@Service
@Transactional
public class AuthClientApiServiceImpl implements AuthClientApiService {

    private static Logger logger = LoggerFactory.getLogger(AuthClientApiServiceImpl.class);

    private static String SYSTEM_ID = PropertiesFileUtil.getInstance("config").get("upms.system.id");

    @Autowired
    AuthClientUserMapper authClientUserMapper;

    @Autowired
    AuthClientApiMapper authClientApiMapper;

    /**
     * 根据用户id获取所拥有的权限
     * @param upmsUserId
     * @return
     */
    @Override
    public List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId) {
        // 用户不存在或锁定状态
        UpmsUser upmsUser = authClientUserMapper.selectByPrimaryKey(upmsUserId);
        if (null == upmsUser || 1 == upmsUser.getLocked()) {
            logger.info("selectUpmsPermissionByUpmsUserId : upmsUserId={}", upmsUserId);
            return null;
        }
        List<UpmsPermission> upmsPermissions = authClientApiMapper.selectUpmsPermissionByUpmsUserId(upmsUserId,Integer.parseInt(SYSTEM_ID));
        return upmsPermissions;
    }

    /**
     * 根据用户id获取所属的角色
     * @param upmsUserId
     * @return
     */
    @Override
    public List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId) {
        // 用户不存在或锁定状态
        UpmsUser upmsUser = authClientUserMapper.selectByPrimaryKey(upmsUserId);
        if (null == upmsUser || 1 == upmsUser.getLocked()) {
            logger.info("selectUpmsRoleByUpmsUserId : upmsUserId={}", upmsUserId);
            return null;
        }
        List<UpmsRole> upmsRoles = authClientApiMapper.selectUpmsRoleByUpmsUserId(upmsUserId);
        return upmsRoles;
    }

    /**
     * 根据username获取UpmsUser
     * @param username
     * @return
     */
    @Override
    public UpmsUser selectUpmsUserByUsername(String username) {
        UpmsUserExample upmsUserExample = new UpmsUserExample();
        upmsUserExample.createCriteria().andUsernameEqualTo(username);
        List<UpmsUser> upmsUsers = authClientUserMapper.selectByExample(upmsUserExample);
        if (null != upmsUsers && upmsUsers.size() > 0) {
            return upmsUsers.get(0);
        }
        return null;
    }

}