package com.lambo.ndp.dao.api;

import java.util.List;
import java.util.Map;

public interface DictMapper {
    List<Map<String,String>> getDictMap(Map<String, String> param);
    Map<String,String> getDictValue(Map<String, String> param);
}