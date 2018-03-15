package com.lambo.ndp.service.imp;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.ndp.dao.api.DictMapper;
import com.lambo.ndp.dao.api.SubjectMapper;
import com.lambo.ndp.model.Dict;
import com.lambo.ndp.model.DictExample;
import com.lambo.ndp.model.Subject;
import com.lambo.ndp.model.SubjectExample;
import com.lambo.ndp.service.api.DictService;
import com.lambo.ndp.service.api.SubjectService;
import org.apache.ibatis.annotations.Mapper;
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
public class SubjectServiceImpl extends BaseServiceImpl<SubjectMapper, Subject, SubjectExample> implements SubjectService {

    private static Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);

    @Autowired
    SubjectMapper subjectMapper;
    public List<Map<String,Object>> querySubject(Map<String, Object> param)
    {
        return subjectMapper.querySubject(param);
    }
}