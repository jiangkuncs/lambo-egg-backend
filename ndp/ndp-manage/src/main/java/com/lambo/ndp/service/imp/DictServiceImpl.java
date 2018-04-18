package com.lambo.ndp.service.imp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.common.validator.LengthValidator;
import com.lambo.ndp.constant.NdpResult;
import com.lambo.ndp.constant.NdpResultConstant;
import com.lambo.ndp.dao.api.DictMapper;
import com.lambo.ndp.model.Dict;
import com.lambo.ndp.model.DictExample;
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
* Created by zxc on 2018/3/10.
*/
@Service
@Transactional
public class DictServiceImpl extends BaseServiceImpl<DictMapper, Dict, DictExample> implements DictService {

    private static Logger logger = LoggerFactory.getLogger(DictServiceImpl.class);

    @Autowired
    DictMapper dictMapper;
    public List<Dict> selectByDictId(String dictId){
        return dictMapper.selectByDictId(dictId);
    }
    public int deleteByDictId(Map parm){
        return dictMapper.deleteByDictId(parm);
    }
    public Object update(String dictId,String dictName,String dictDesc,String dictKeyList ){
        int count = 0;
        ComplexResult result = FluentValidator.checkAll()
                .on(dictId, new LengthValidator(1, 20, "表名"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new NdpResult(NdpResultConstant.INVALID_LENGTH, result.getErrors());
        }
        //System.out.println("dictKeyList:"+dictKeyList);

        JSONArray json = JSONArray.parseArray(dictKeyList);
        System.out.println("json:"+json);
        if(json.size()>0){
            Map parm=new HashMap();
            parm.put("dictId",dictId);
            count = dictMapper.deleteByDictId(parm);
            for(int i=0;i<json.size();i++){
                JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                System.out.println("job:"+job) ;  // 得到 每个对象中的属性值
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
}