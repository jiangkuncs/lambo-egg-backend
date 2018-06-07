package com.lambo.rest.client.dao.api;

import com.lambo.rest.client.model.RestDatasource;
import com.lambo.rest.client.model.RestDatasourceExample;
import java.util.List;

public interface RestClientDatasourceMapper {
    int deleteByPrimaryKey(String dsId);

    int insert(RestDatasource record);

    int insertSelective(RestDatasource record);

    List<RestDatasource> selectByExample(RestDatasourceExample example);

    RestDatasource selectByPrimaryKey(String dsId);

    int updateByPrimaryKeySelective(RestDatasource record);

    int updateByPrimaryKey(RestDatasource record);
}