package com.lambo.rest.client.service.impl;

import com.lambo.common.db.DynamicDataSource;
import com.lambo.rest.client.constant.OprationTypeEnum;
import com.lambo.rest.client.dao.api.RestClientCommonExcutorMapper;
import com.lambo.rest.client.factory.SqlFactory;
import com.lambo.rest.client.model.RestSetting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RestClientServiceImpl
 * @Descirption TODO
 * @Author sunzhen
 * @Date 2018/5/11 16:16
 **/
public class RestClientServiceImpl {

    private final static Logger logger = LoggerFactory.getLogger(RestClientServiceImpl.class);

    @Autowired
    RestClientCommonExcutorMapper restClientCommonExcutorMapper;

    Object getResult(RestSetting restSetting, Map paramMap, Boolean mock){
        String sqlTemplate ;
        if(mock){
            sqlTemplate = restSetting.getMockData();
            String data = SqlFactory.generateSql(sqlTemplate,paramMap);
            return data;
        }else{
            sqlTemplate = restSetting.getRestSql();
            String sql = SqlFactory.generateSql(sqlTemplate,paramMap);
            return excutor(sql,restSetting.getOprationType(),restSetting.getDatasource());
        }
    }

    /**
     * sql执行
     * @param sql
     * @param opration_type
     * @return
     */
    Object excutor(String sql,String opration_type,String datasource){

        DynamicDataSource.setDataSource(datasource);
        try{
            if(OprationTypeEnum.SELECT_LIST.getName().equals(opration_type)){
                return  restClientCommonExcutorMapper.select(sql);
            }else if(OprationTypeEnum.SELECT_ONE.getName().equals(opration_type)){
                List list = restClientCommonExcutorMapper.select(sql);
                if(list != null && list.size() > 0){
                    return list.get(0);
                }else{
                    return new HashMap();
                }
            }else if(OprationTypeEnum.UPDATE.getName().equals(opration_type)){
                return  restClientCommonExcutorMapper.update(sql);
            }else if(OprationTypeEnum.INSERT.getName().equals(opration_type)){
                return  restClientCommonExcutorMapper.insert(sql);
            }else if(OprationTypeEnum.DELETE.getName().equals(opration_type)){
                return  restClientCommonExcutorMapper.delete(sql);
            }else{
                throw new RuntimeException("不识别的操作类型："+opration_type);
            }
        }catch(Exception e){
            logger.error("sql执行出错",e);
        }finally {
            DynamicDataSource.clearDataSource();
        }

        return null;
    }
}
