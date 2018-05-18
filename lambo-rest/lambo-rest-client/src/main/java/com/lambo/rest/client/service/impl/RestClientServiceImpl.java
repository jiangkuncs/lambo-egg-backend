package com.lambo.rest.client.service.impl;

import com.alibaba.fastjson.JSON;
import com.lambo.common.db.DynamicDataSource;
import com.lambo.rest.client.constant.OprationTypeEnum;
import com.lambo.rest.client.dao.api.RestClientCommonExcutorMapper;
import com.lambo.rest.client.factory.SqlFactory;
import com.lambo.rest.client.model.RestSetting;
import com.lambo.rest.client.service.api.RestClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RestClientServiceImpl
 * @Descirption TODO
 * @Author sunzhen
 * @Date 2018/5/11 16:16
 **/
@Service
public class RestClientServiceImpl implements RestClientService {

    private final static Logger logger = LoggerFactory.getLogger(RestClientServiceImpl.class);
    @Autowired
    RestClientCommonExcutorMapper restClientCommonExcutorMapper;

    @Override
    public Object getResult(RestSetting restSetting, Map paramMap, Boolean mock){
        String sqlTemplate ;
        if(mock){
            sqlTemplate = restSetting.getMockData();
            String data = SqlFactory.generateSql(sqlTemplate,paramMap);
            String operation_type = restSetting.getOperationType();
            if(OprationTypeEnum.SELECT_LIST.getName().equals(operation_type)){
                return JSON.parseArray(data);
            }else if(OprationTypeEnum.SELECT_ONE.getName().equals(operation_type)){
                return JSON.parseObject(data);
            }
            try{
                return Integer.parseInt(data);
            }catch(Exception e){
                return data;
            }

        }else{
            sqlTemplate = restSetting.getRestSql();
            String sql = SqlFactory.generateSql(sqlTemplate,paramMap);
            if(logger.isDebugEnabled()){
                logger.debug("执行SQL为:"+sql);
                logger.debug("操作类型为:"+restSetting.getOperationType());
                logger.debug("数据源为:"+restSetting.getDatasource());
            }
            return excutor(sql,restSetting.getOperationType(),restSetting.getDatasource());
        }
    }

    /**
     * sql执行
     * @param sql
     * @param operation_type
     * @return
     */
    @Override
    public Object excutor(String sql,String operation_type,String datasource){

        DynamicDataSource.setDataSource(datasource);
        try{
            if(OprationTypeEnum.SELECT_LIST.getName().equals(operation_type)){
                return  restClientCommonExcutorMapper.select(sql);
            }else if(OprationTypeEnum.SELECT_ONE.getName().equals(operation_type)){
                List list = restClientCommonExcutorMapper.select(sql);
                if(list != null && list.size() > 0){
                    return list.get(0);
                }else{
                    return new HashMap();
                }
            }else if(OprationTypeEnum.UPDATE.getName().equals(operation_type)){
                return  restClientCommonExcutorMapper.update(sql);
            }else if(OprationTypeEnum.INSERT.getName().equals(operation_type)){
                return  restClientCommonExcutorMapper.insert(sql);
            }else if(OprationTypeEnum.DELETE.getName().equals(operation_type)){
                return  restClientCommonExcutorMapper.delete(sql);
            }else{
                throw new RuntimeException("不识别的操作类型："+operation_type);
            }
        }catch(Exception e){
            logger.error("sql执行出错",e);
            throw new RuntimeException(e);
        }finally {
            DynamicDataSource.clearDataSource();
        }
    }
}
