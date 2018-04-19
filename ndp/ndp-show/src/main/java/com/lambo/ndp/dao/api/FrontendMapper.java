package com.lambo.ndp.dao.api;

import java.util.List;
import java.util.Map;

public interface FrontendMapper {

    List getCategoryList(Map param);

    List getCategoryAndSubjectList(Map param);

    List getSubjectList(Map param);

    List getSubjectInfo(Map param);

    List getSqlData(Map param);

    Map getDimensionInfo(Integer dimension_id);

    List getProviceData(Map param);

    List getCityData(Map param);

    List getItemData(Map param);

    List getBrandData(Map param);
}