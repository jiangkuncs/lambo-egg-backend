package com.lambo.ndp.dao.api;

import com.lambo.ndp.model.Dict;
import com.lambo.ndp.model.DictExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DictMapper {
    int deleteByDictIdDictKey(String dictId, String dictKey);
    int deleteByDictId(Map parm);

    int insert(Dict record);

    int insertSelective(Dict record);

    List<Dict> selectByExample(DictExample example);

    List<Dict> selectByDictId(String dictId);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);
}