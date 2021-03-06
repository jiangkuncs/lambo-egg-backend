package com.lambo.ndp.dao.api;

import java.util.List;
import java.util.Map;

public interface HomepageMapper {
    List<Map<String,Object>> getHotSubject(Map<String, Object> param);
    List<Map<String,Object>> getNewSubject(Map<String, Object> param);
    List<Map<String,Object>> getAllCategory(Map<String, Object> param);
    Map<String,Object> getCategoryNum(Map<String, Object> param);
    Map<String,Object> getFileNum(Map<String, Object> param);
    Map<String,Object> getDataNum(Map<String, Object> param);
    Map<String,Object> getRecordNum(Map<String, Object> param);
    Map<String,Object> getDownloadNum(Map<String, Object> param);
}