package com.lambo.ndp.dao.api;


import java.util.List;
import java.util.Map;


public interface DataViewMapper {

    List<Map<String,String>> getDictListByDictId(String dictId);
    List<Map<String,String>> getSearchResult(String sql);
    int updateRateCountBySubjectId(Map<String,String> paraMap );
    int updateVisitCountBySubjectId(int paraMap);
}