package com.lambo.ndp.service.api;

import com.lambo.common.base.BaseService;
import com.lambo.ndp.model.Dict;
import com.lambo.ndp.model.DictExample;

import java.util.List;
import java.util.Map;


/**
 * DictService接口
 * Created by zxc on 2018/3/10.
*/
public interface DictService extends BaseService<Dict, DictExample> {
    public List<Dict> selectByDictId(String dictId);
    public int deleteByDictId(Map parm);
    public Object update(String dictId,String dictName,String dictDesc,String dictKeyList );
}