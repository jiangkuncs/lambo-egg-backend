package com.lambo.upms.client.dao.api;

import com.lambo.upms.client.dao.model.UpmsUserPermission;
import com.lambo.upms.client.dao.model.UpmsUserPermissionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpmsUserPermissionMapper {
    long countByExample(UpmsUserPermissionExample example);

    int deleteByExample(UpmsUserPermissionExample example);

    int deleteByPrimaryKey(Integer userPermissionId);

    int insert(UpmsUserPermission record);

    int insertSelective(UpmsUserPermission record);

    List<UpmsUserPermission> selectByExample(UpmsUserPermissionExample example);

    UpmsUserPermission selectByPrimaryKey(Integer userPermissionId);

    int updateByExampleSelective(@Param("record") UpmsUserPermission record, @Param("example") UpmsUserPermissionExample example);

    int updateByExample(@Param("record") UpmsUserPermission record, @Param("example") UpmsUserPermissionExample example);

    int updateByPrimaryKeySelective(UpmsUserPermission record);

    int updateByPrimaryKey(UpmsUserPermission record);
}