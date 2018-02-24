package com.lambo.upms.client.dao.api;

import com.lambo.upms.client.dao.model.UpmsRolePermission;
import com.lambo.upms.client.dao.model.UpmsRolePermissionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpmsRolePermissionMapper {
    long countByExample(UpmsRolePermissionExample example);

    int deleteByExample(UpmsRolePermissionExample example);

    int deleteByPrimaryKey(Integer rolePermissionId);

    int insert(UpmsRolePermission record);

    int insertSelective(UpmsRolePermission record);

    List<UpmsRolePermission> selectByExample(UpmsRolePermissionExample example);

    UpmsRolePermission selectByPrimaryKey(Integer rolePermissionId);

    int updateByExampleSelective(@Param("record") UpmsRolePermission record, @Param("example") UpmsRolePermissionExample example);

    int updateByExample(@Param("record") UpmsRolePermission record, @Param("example") UpmsRolePermissionExample example);

    int updateByPrimaryKeySelective(UpmsRolePermission record);

    int updateByPrimaryKey(UpmsRolePermission record);
}