package com.lambo.rest.manage.dao.api;

import com.lambo.rest.manage.model.RestDatasource;
import com.lambo.rest.manage.model.RestDatasourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RestDatasourceMapper {
    int deleteByExample(RestDatasourceExample example);

    int deleteByPrimaryKey(String dsId);

    int insert(RestDatasource record);

    int insertSelective(RestDatasource record);

    List<RestDatasource> selectByExample(RestDatasourceExample example);

    RestDatasource selectByPrimaryKey(String dsId);

    int updateByExampleSelective(@Param("record") RestDatasource record, @Param("example") RestDatasourceExample example);

    int updateByExample(@Param("record") RestDatasource record, @Param("example") RestDatasourceExample example);

    int updateByPrimaryKeySelective(RestDatasource record);

    int updateByPrimaryKey(RestDatasource record);
}