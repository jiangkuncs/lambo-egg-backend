package com.lambo.ndp.service.imp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.ndp.dao.api.DictMapper;
import com.lambo.ndp.model.Dict;
import com.lambo.ndp.model.DictExample;
import com.lambo.ndp.service.api.DictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
* DictService实现
* Created by zxc on 2018/3/10.
*/
@Service
@BaseService
public class DictServiceImpl extends BaseServiceImpl<DictMapper, Dict, DictExample> implements DictService {

    private static Logger logger = LoggerFactory.getLogger(DictServiceImpl.class);

    @Autowired
    DictMapper dictMapper;

    @Override
    public Object update(String dictId,String dictName,String dictDesc,String dictKeyList ){
        int count = 0;
        JSONArray json = JSONArray.parseArray(dictKeyList);
        if(json.size()>0){
            DictExample dictExample = new DictExample();
            dictExample.createCriteria().andDictIdEqualTo(dictId);
            count = dictMapper.deleteByExample(dictExample);
            for(int i=0;i<json.size();i++){
                JSONObject job = json.getJSONObject(i);
                Dict dict=new Dict();
                dict.setDictId(dictId);
                dict.setDictDesc(dictDesc);
                dict.setDictName(dictName);
                dict.setDictKey((String)job.get("dictKey"));
                dict.setDictValue((String)job.get("dictValue"));
                dictMapper.insert(dict);
            }
        }
        return count;
    }
    public List<Map<String,Object>> getDict(Map parm ){
        return dictMapper.getDict(parm);
    }
}