package com.lambo.ndp.dao.api;

import com.lambo.ndp.model.CateGory;
import com.lambo.ndp.model.CateGoryExample;
import java.util.List;

public interface CateGoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(CateGory record);

    int insertSelective(CateGory record);

    List<CateGory> selectByExample(CateGoryExample example);

    CateGory selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(CateGory record);

    int updateByPrimaryKey(CateGory record);
}