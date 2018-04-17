package com.lambo.ndp.dao.api;

import com.lambo.ndp.model.CategoryOverview;
import com.lambo.ndp.model.CategoryOverviewExample;
import java.util.List;

public interface CategoryOverviewMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(CategoryOverview record);

    int insertSelective(CategoryOverview record);

    List<CategoryOverview> selectByExampleWithBLOBs(CategoryOverviewExample example);

    List<CategoryOverview> selectByExample(CategoryOverviewExample example);

    CategoryOverview selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(CategoryOverview record);

    int updateByPrimaryKeyWithBLOBs(CategoryOverview record);

    int updateByPrimaryKey(CategoryOverview record);
}