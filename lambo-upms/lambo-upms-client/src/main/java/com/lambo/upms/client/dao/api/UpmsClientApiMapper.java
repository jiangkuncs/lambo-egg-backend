package com.lambo.upms.client.dao.api;


import com.lambo.upms.client.dao.model.UpmsPermission;
import com.lambo.upms.client.dao.model.UpmsRole;

import java.util.List;

/**
 * 用户VOMapper
 * Created by lambo on 2017/01/07.
 */
public interface UpmsClientApiMapper {

	// 根据用户id获取所拥有的权限
	List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId);

	// 根据用户id获取所属的角色
	List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId);
	
}