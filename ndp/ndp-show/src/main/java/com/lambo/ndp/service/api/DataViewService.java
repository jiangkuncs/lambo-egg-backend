package com.lambo.ndp.service.api;


import com.lambo.ndp.model.DictExample;
import com.lambo.ndp.model.Dict;
import com.lambo.common.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * DictService接口
 * Created by zxc on 2018/3/10.
*/
public interface DataViewService extends BaseService<Dict, DictExample> {
    //根据DictId查询枚举值
    List<Map<String, String>> getDictListByDictId(String dict);
    //数据目录筛选条件
    Map<String,List<Map<String,String>>> getConditionMap();
    //查询数据目录
    List<Map<String, String>> getSearchResult(Map condition);
}