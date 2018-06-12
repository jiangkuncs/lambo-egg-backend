package com.lambo.rest.manage.dao.api;

import com.lambo.rest.manage.model.RestSettingParamsLog;
import com.lambo.rest.manage.model.RestSettingParamsLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RestSettingParamsLogMapper {
    int deleteByExample(RestSettingParamsLogExample example);

    int deleteByPrimaryKey(@Param("logId") String logId, @Param("restId") String restId, @Param("paramKey") String paramKey);

    int insert(RestSettingParamsLog record);

    int insertSelective(RestSettingParamsLog record);

    List<RestSettingParamsLog> selectByExample(RestSettingParamsLogExample example);

    RestSettingParamsLog selectByPrimaryKey(@Param("logId") String logId, @Param("restId") String restId, @Param("paramKey") String paramKey);

    int updateByExampleSelective(@Param("record") RestSettingParamsLog record, @Param("example") RestSettingParamsLogExample example);

    int updateByExample(@Param("record") RestSettingParamsLog record, @Param("example") RestSettingParamsLogExample example);

    int updateByPrimaryKeySelective(RestSettingParamsLog record);

    int updateByPrimaryKey(RestSettingParamsLog record);
}