package com.lambo.ndp.service.imp;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.ndp.dao.api.DictMapper;
import com.lambo.ndp.service.api.DictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DictService实现
 * Created by zxc on 2018/2/28.
*/
@Service
@Transactional
@BaseService
public class DictServiceImpl extends BaseServiceImpl<DictMapper,Map, Map> implements DictService {

    public static Map<String, Map<String, String>> dictMap = new HashMap<>();
    private static Logger logger = LoggerFactory.getLogger(DictServiceImpl.class);

    @Autowired
    DictMapper dictMapper;

    public Map<String,String> getDictMap(String dictId){
        Map<String, String> result = dictMap.get(dictId);
        if (result == null || result.isEmpty()){
            Map<String, String> param = new HashMap<>();
            param.put("DICT_ID", dictId);
            List<Map<String,String>> list = dictMapper.getDictMap(param);
            if (list != null && list.size() > 0){
                result = new HashMap<>();
                for (Map<String,String> map : list){
                    result.put(map.get("DICT_KEY"),map.get("DICT_VALUE"));
                }
                dictMap.put(dictId, result);
            }
        }
        return result;
    }

    public String getDictValue(String dictId,String dictKey){
        return getDictMap(dictId).get(dictKey);
    }

}