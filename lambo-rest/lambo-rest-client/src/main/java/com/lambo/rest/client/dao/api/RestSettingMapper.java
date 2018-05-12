package com.lambo.rest.client.dao.api;

import com.lambo.rest.client.model.RestSetting;
import com.lambo.rest.client.model.RestSettingExample;
import java.util.List;

public interface RestSettingMapper {
    int deleteByPrimaryKey(String restId);

    int insert(RestSetting record);

    int insertSelective(RestSetting record);

    List<RestSetting> selectByExampleWithBLOBs(RestSettingExample example);

    List<RestSetting> selectByExample(RestSettingExample example);

    RestSetting selectByPrimaryKey(String restId);

    int updateByPrimaryKeySelective(RestSetting record);

    int updateByPrimaryKeyWithBLOBs(RestSetting record);

    int updateByPrimaryKey(RestSetting record);
}