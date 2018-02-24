package com.lambo.upms.client.dao.api;

import com.lambo.upms.client.dao.model.UpmsUserOrganization;
import com.lambo.upms.client.dao.model.UpmsUserOrganizationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UpmsUserOrganizationMapper {
    long countByExample(UpmsUserOrganizationExample example);

    int deleteByExample(UpmsUserOrganizationExample example);

    int deleteByPrimaryKey(Integer userOrganizationId);

    int insert(UpmsUserOrganization record);

    int insertSelective(UpmsUserOrganization record);

    List<UpmsUserOrganization> selectByExample(UpmsUserOrganizationExample example);

    UpmsUserOrganization selectByPrimaryKey(Integer userOrganizationId);

    int updateByExampleSelective(@Param("record") UpmsUserOrganization record, @Param("example") UpmsUserOrganizationExample example);

    int updateByExample(@Param("record") UpmsUserOrganization record, @Param("example") UpmsUserOrganizationExample example);

    int updateByPrimaryKeySelective(UpmsUserOrganization record);

    int updateByPrimaryKey(UpmsUserOrganization record);
}