package com.lambo.demo.sample.dao.mapper;

import com.lambo.demo.sample.dao.model.LogDemo;
import com.lambo.demo.sample.dao.model.LogDemoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogDemoMapper {
    long countByExample(LogDemoExample example);

    int deleteByExample(LogDemoExample example);

    int deleteByPrimaryKey(Integer logId);

    int insert(LogDemo record);

    int insertSelective(LogDemo record);

    List<LogDemo> selectByExampleWithBLOBs(LogDemoExample example);

    List<LogDemo> selectByExample(LogDemoExample example);

    LogDemo selectByPrimaryKey(Integer logId);

    int updateByExampleSelective(@Param("record") LogDemo record, @Param("example") LogDemoExample example);

    int updateByExampleWithBLOBs(@Param("record") LogDemo record, @Param("example") LogDemoExample example);

    int updateByExample(@Param("record") LogDemo record, @Param("example") LogDemoExample example);

    int updateByPrimaryKeySelective(LogDemo record);

    int updateByPrimaryKeyWithBLOBs(LogDemo record);

    int updateByPrimaryKey(LogDemo record);
}