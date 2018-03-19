package com.lambo.ndp.service.imp;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.ndp.dao.api.CateGoryMapper;
import com.lambo.ndp.dao.api.DemoLogMapper;
import com.lambo.ndp.model.CateGory;
import com.lambo.ndp.model.CateGoryExample;
import com.lambo.ndp.model.DemoLog;
import com.lambo.ndp.model.DemoLogExample;
import com.lambo.ndp.service.api.CateGoryService;
import com.lambo.ndp.service.api.DemoLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CateGoryService实现
 * Created by zxc on 2018/2/28.
*/
@Service
@Transactional
@BaseService
public class CateGoryServiceImpl extends BaseServiceImpl<CateGoryMapper, CateGory, CateGoryExample> implements CateGoryService {

    private static Logger logger = LoggerFactory.getLogger(CateGoryServiceImpl.class);

    @Autowired
    CateGoryMapper cateGoryMapper;

}