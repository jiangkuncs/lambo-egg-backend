package com.lambo.ndp.dao;

import java.util.List;
import java.util.Map;

public interface FrontendMapper {

    List getCategoryList(Map param);

    List getSubjectList(Map param);

    List getSubjectInfo(Map param);

    List getTableData(Map param);

    Integer getTableDataCount(Map param);

    Map getDimensionInfo(Integer dimension_id);

    List getDimensionData(Map param);
}