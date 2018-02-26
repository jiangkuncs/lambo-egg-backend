package com.lambo.upms.server.service.impl;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.upms.server.dao.api.*;
import com.lambo.upms.server.dao.model.*;
import com.lambo.upms.server.service.api.UpmsSystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* UpmsSystemService实现
* Created by lambo on 2017/3/20.
*/
@Service
@Transactional
@BaseService
public class UpmsSystemServiceImpl extends BaseServiceImpl<UpmsSystemMapper, UpmsSystem, UpmsSystemExample> implements UpmsSystemService {

    private static Logger logger = LoggerFactory.getLogger(UpmsSystemServiceImpl.class);

    @Autowired
    UpmsSystemMapper upmsSystemMapper;

    @Override
    public UpmsSystem selectUpmsSystemByName(String name) {
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria()
                .andNameEqualTo(name);
        List<UpmsSystem> upmsSystems = upmsSystemMapper.selectByExample(upmsSystemExample);
        if (null != upmsSystems && upmsSystems.size() > 0) {
            return upmsSystems.get(0);
        }
        return null;
    }

}