package com.lambo.auth.client.service.api;

import com.lambo.auth.client.dao.model.UpmsPermission;
import com.lambo.auth.client.dao.model.UpmsRole;
import com.lambo.auth.client.dao.model.UpmsUser;

import java.util.List;

/**
 * upms系统接口
 * Created by lambo on 2017/2/11.
 */
public interface AuthClientApiService {

    /**
     * 根据username获取UpmsUser
     * @param username
     * @return
     */
    UpmsUser selectUpmsUserByUsername(String username);


    /**
     * 根据用户id获取所属的角色
     * @param upmsUserId
     * @return
     */
    List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId);

    /**
     * 根据用户id获取所拥有的权限(用户和角色权限合集)
     * @param upmsUserId
     * @return
     */
    List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId);

}
