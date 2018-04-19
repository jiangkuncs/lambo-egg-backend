package com.lambo.ndp.service.imp;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.ndp.dao.api.HomepageMapper;
import com.lambo.ndp.service.api.HomepageService;
import com.lambo.ndp.service.api.HomepageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HomepageService实现
 * Created by zxc on 2018/2/28.
*/
@Service
@Transactional
@BaseService
public class HomepageServiceImpl extends BaseServiceImpl<HomepageMapper,Map, Map> implements HomepageService {

    private static Logger logger = LoggerFactory.getLogger(HomepageServiceImpl.class);

    @Autowired
    HomepageMapper homepageMapper;

    public List<Map<String,Object>> getHotSubject(Map<String, Object> param){
        return homepageMapper.getHotSubject(param);
    }

    public List<Map<String,Object>> getNewSubject(Map<String, Object> param){
        return homepageMapper.getNewSubject(param);
    }

    public List<Map<String,Object>> getAllCategory(Map<String, Object> param){
        return homepageMapper.getAllCategory(param);
    }

    public Map<String,Object> getDataStatistics(Map<String, Object> param){
        Map<String, Object> result = homepageMapper.getSubjectNum(param);
        result.putAll(homepageMapper.getRecordNum(param));
        return result;
    }

}