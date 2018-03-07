package com.lambo.demo.service.impl;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.demo.dao.api.DemoLogMapper;
import com.lambo.demo.model.DemoLog;
import com.lambo.demo.model.DemoLogExample;
import com.lambo.demo.service.api.DemoLogService;
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

}