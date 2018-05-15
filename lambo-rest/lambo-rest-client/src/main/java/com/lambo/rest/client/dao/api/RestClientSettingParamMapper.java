package com.lambo.rest.client.dao.api;

import com.lambo.rest.client.model.RestSettingParam;
import com.lambo.rest.client.model.RestSettingParamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RestClientSettingParamMapper {
    int deleteByPrimaryKey(@Param("restId") String restId, @Param("paramKey") String paramKey);

    int insert(RestSettingParam record);

    int insertSelective(RestSettingParam record);

    List<RestSettingParam> selectByExample(RestSettingParamExample example);

    RestSettingParam selectByPrimaryKey(@Param("restId") String restId, @Param("paramKey") String paramKey);

    int updateByPrimaryKeySelective(RestSettingParam record);

    int updateByPrimaryKey(RestSettingParam record);
}