package com.lambo.ndp.service.imp;


import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.ndp.constant.DictConstants;
import com.lambo.ndp.dao.api.DataViewMapper;
import com.lambo.ndp.model.Dict;
import com.lambo.ndp.model.DictExample;
import com.lambo.ndp.service.api.DataViewService;
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
@BaseService
public class DataViewServiceImpl extends BaseServiceImpl<DataViewMapper,Dict, DictExample> implements DataViewService {

    private static Logger logger = LoggerFactory.getLogger( DataViewServiceImpl.class);

    @Autowired
    private DataViewMapper dataViewMapper;

    @Override
    public List<Map<String, String>> getDictListByDictId(String dictId) {
        if(logger.isDebugEnabled()){
            logger.debug("根据dict_id查询枚举值");
        }
        List<Map<String,String>> resultlist = dataViewMapper.getDictListByDictId(dictId);
        return resultlist;
    }

    @Override
    public Map<String, List<Map<String, String>>> getConditionMap() {
        //获取分类
        List<Map<String,String>> categoryTypeList = dataViewMapper.getDictListByDictId(DictConstants.CATEGORY_DICT_ID);

        //获取时间粒度
        List<Map<String,String>> timeTypeList = dataViewMapper.getDictListByDictId(DictConstants.TIME_DICT_ID);

        //获取组织粒度
        List<Map<String,String>> organTypeList = dataViewMapper.getDictListByDictId(DictConstants.ORGAN_DICT_ID);

        //获取指标
        List<Map<String,String>> indexTypeList = dataViewMapper.getDictListByDictId(DictConstants.INDEX_DICT_ID);

        Map<String, List<Map<String, String>>> resultMap = new HashMap<>();
        resultMap.put("categoryTypeList",categoryTypeList);
        resultMap.put("timeTypeList",timeTypeList);
        resultMap.put("organTypeList",organTypeList);
        resultMap.put("indexTypeList",indexTypeList);
        return resultMap;
    }

    @Override
    public List<Map<String, String>> getSearchResult(Map condition) {
        return null;
    }

}