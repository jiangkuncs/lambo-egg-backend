package com.lambo.ndp.service.imp;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;

import com.lambo.ndp.dao.api.DemoLogMapper;
import com.lambo.ndp.model.DemoLog;
import com.lambo.ndp.model.DemoLogExample;
import com.lambo.ndp.service.api.DemoLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsLogService实现
* Created by lambo on 2017/3/20.
*/
@Service
@Transactional
@BaseService
public class DemoLogServiceImpl extends BaseServiceImpl<DemoLogMapper, DemoLog, DemoLogExample> implements DemoLogService {

    private static Logger logger = LoggerFactory.getLogger(DemoLogServiceImpl.class);

    @Autowired
    DemoLogMapper upmsLogMapper;

}