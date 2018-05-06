package com.lambo.ndp.dao.api;

import com.lambo.ndp.model.Dict;
import com.lambo.ndp.model.DictExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DictMapper {
    int deleteByExample(DictExample example);

    int deleteByPrimaryKey(@Param("dictId") String dictId, @Param("dictKey") String dictKey);

    int insert(Dict record);

    int insertSelective(Dict record);

    List<Dict> selectByExample(DictExample example);

    Dict selectByPrimaryKey(@Param("dictId") String dictId, @Param("dictKey") String dictKey);

    int updateByExampleSelective(@Param("record") Dict record, @Param("example") DictExample example);

    int updateByExample(@Param("record") Dict record, @Param("example") DictExample example);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);


}