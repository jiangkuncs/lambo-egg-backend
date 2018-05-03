package com.lambo.ndp.dao.api;

import com.lambo.ndp.model.DemoLog;
import com.lambo.ndp.model.DemoLogExample;
import com.lambo.ndp.model.DemoLog;

import java.util.List;

public interface DemoLogMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(DemoLog record);

    int insertSelective(DemoLog record);

    List<DemoLog> selectByExampleWithBLOBs(DemoLogExample example);

    List<DemoLog> selectByExample(DemoLogExample example);

    DemoLog selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(DemoLog record);

    int updateByPrimaryKeyWithBLOBs(DemoLog record);

    int updateByPrimaryKey(DemoLog record);
}