package com.lambo.rest.manage.dao.api;

import com.lambo.rest.manage.model.RestSettingLog;
import com.lambo.rest.manage.model.RestSettingLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RestSettingLogMapper {
    int deleteByExample(RestSettingLogExample example);

    int deleteByPrimaryKey(String logId);

    int insert(RestSettingLog record);

    int insertSelective(RestSettingLog record);

    List<RestSettingLog> selectByExampleWithBLOBs(RestSettingLogExample example);

    List<RestSettingLog> selectByExample(RestSettingLogExample example);

    RestSettingLog selectByPrimaryKey(String logId);

    int updateByExampleSelective(@Param("record") RestSettingLog record, @Param("example") RestSettingLogExample example);

    int updateByExampleWithBLOBs(@Param("record") RestSettingLog record, @Param("example") RestSettingLogExample example);

    int updateByExample(@Param("record") RestSettingLog record, @Param("example") RestSettingLogExample example);

    int updateByPrimaryKeySelective(RestSettingLog record);

    int updateByPrimaryKeyWithBLOBs(RestSettingLog record);

    int updateByPrimaryKey(RestSettingLog record);
}