package com.lambo.ndp.service.api;

import com.lambo.common.base.BaseService;
import com.lambo.ndp.model.Dict;
import com.lambo.ndp.model.DictExample;

import java.util.List;
import java.util.Map;


/**
* UpmsLogService接口
* Created by lambo on 2017/3/20.
*/
public interface DictService extends BaseService<Dict, DictExample> {
    public List<Dict> selectByDictId(String dictId);
    public int deleteByDictId(Map parm);
}