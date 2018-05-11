package com.lambo.rest.manage.dao.api;

import com.lambo.rest.manage.model.RestStru;
import com.lambo.rest.manage.model.RestStruExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RestStruMapper {
    int deleteByExample(RestStruExample example);

    int deleteByPrimaryKey(String struId);

    int insert(RestStru record);

    int insertSelective(RestStru record);

    List<RestStru> selectByExample(RestStruExample example);

    RestStru selectByPrimaryKey(String struId);

    int updateByExampleSelective(@Param("record") RestStru record, @Param("example") RestStruExample example);

    int updateByExample(@Param("record") RestStru record, @Param("example") RestStruExample example);

    int updateByPrimaryKeySelective(RestStru record);

    int updateByPrimaryKey(RestStru record);
}