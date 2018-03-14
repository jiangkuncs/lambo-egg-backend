package com.lambo.ndp.service.imp;

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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
* UpmsLogService实现
* Created by lambo on 2017/3/20.
*/
@Service
@Transactional
@BaseService
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
}