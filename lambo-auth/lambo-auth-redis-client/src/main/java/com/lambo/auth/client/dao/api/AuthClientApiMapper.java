package com.lambo.auth.client.dao.api;


import com.lambo.auth.client.dao.model.UpmsRole;
import com.lambo.auth.client.dao.model.UpmsPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户VOMapper
 * Created by lambo on 2017/01/07.
 */
public interface AuthClientApiMapper {

	/**
	 * 根据用户id获取所拥有的权限
	 * @param upmsUserId
	 * @param systemId
	 * @return
	 */
	List<UpmsPermission> selectUpmsPermissionByUpmsUserId(@Param("upmsUserId") Integer upmsUserId, @Param("systemId") Integer systemId);

	/**
	 * 根据用户id获取所属的角色
	 * @param upmsUserId
	 * @return
	 */
	List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId);
	
}