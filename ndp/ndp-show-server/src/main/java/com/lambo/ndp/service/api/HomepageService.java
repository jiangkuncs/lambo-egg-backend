package com.lambo.ndp.service.api;

import com.lambo.common.base.BaseService;

import java.util.List;
import java.util.Map;


/**
 * SubjectService接口
 * Created by zxc on 2018/3/15.
*/
public interface HomepageService extends BaseService<Map, Map> {
    List<Map<String,Object>> getHotSubject(Map<String, Object> param);
    List<Map<String,Object>> getNewSubject(Map<String, Object> param);
    List<Map<String,Object>> getAllCategory(Map<String, Object> param);
    Map<String,Object> getDataStatistics(Map<String, Object> param);
}