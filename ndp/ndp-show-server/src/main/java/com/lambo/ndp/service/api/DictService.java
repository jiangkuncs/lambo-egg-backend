package com.lambo.ndp.service.api;

import com.lambo.common.base.BaseService;

import java.util.List;
import java.util.Map;


/**
 * SubjectService接口
 * Created by zxc on 2018/3/15.
*/
public interface DictService extends BaseService<Map, Map> {
    Map<String,String> getDictMap(String dictId);
    String getDictValue(String dictId,String dictKey);
}