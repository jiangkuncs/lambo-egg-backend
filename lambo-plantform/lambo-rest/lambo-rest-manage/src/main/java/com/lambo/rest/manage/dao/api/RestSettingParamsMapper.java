package com.lambo.rest.manage.dao.api;

import com.lambo.rest.manage.model.RestSettingParams;
import com.lambo.rest.manage.model.RestSettingParamsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RestSettingParamsMapper {
    int deleteByExample(RestSettingParamsExample example);

    int deleteByPrimaryKey(@Param("restId") String restId, @Param("paramKey") String paramKey);

    int insert(RestSettingParams record);

    int insertSelective(RestSettingParams record);

    List<RestSettingParams> selectByExample(RestSettingParamsExample example);

    RestSettingParams selectByPrimaryKey(@Param("restId") String restId, @Param("paramKey") String paramKey);

    int updateByExampleSelective(@Param("record") RestSettingParams record, @Param("example") RestSettingParamsExample example);

    int updateByExample(@Param("record") RestSettingParams record, @Param("example") RestSettingParamsExample example);

    int updateByPrimaryKeySelective(RestSettingParams record);

    int updateByPrimaryKey(RestSettingParams record);
}