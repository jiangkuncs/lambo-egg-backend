package com.lambo.rest.client.service.impl;

import com.alibaba.fastjson.JSON;
import com.lambo.common.annotation.LogAround;
import com.lambo.common.base.BaseResult;
import com.lambo.common.db.DatasourceUtil;
import com.lambo.common.db.DynamicDataSource;
import com.lambo.common.utils.codec.AesUtils;
import com.lambo.common.utils.io.PropertiesFileUtil;
import com.lambo.rest.client.constant.OprationTypeEnum;
import com.lambo.rest.client.dao.api.RestClientCommonExcutorMapper;
import com.lambo.rest.client.factory.SqlFactory;
import com.lambo.rest.client.model.RestDatasource;
import com.lambo.rest.client.model.RestDatasourceExample;
import com.lambo.rest.client.model.RestSetting;
import com.lambo.rest.client.service.api.RestClientDatasourceService;
import com.lambo.rest.client.service.api.RestClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    RestClientDatasourceService restClientDatasourceService;

    @Override
    public Object getResult(RestSetting restSetting, Map paramMap, Boolean mock) {
        String sqlTemplate;
        if (mock) {
            sqlTemplate = restSetting.getMockData();
            String data = SqlFactory.generateSql(sqlTemplate, paramMap);
            String operation_type = restSetting.getOperationType();
            if (OprationTypeEnum.SELECT_LIST.getName().equals(operation_type)) {
                return JSON.parseArray(data);
            } else if (OprationTypeEnum.SELECT_ONE.getName().equals(operation_type)) {
                return JSON.parseObject(data);
            }
            try {
                return Integer.parseInt(data);
            } catch (Exception e) {
                return data;
            }

        } else {
            sqlTemplate = restSetting.getRestSql();
            String sql = SqlFactory.generateSql(sqlTemplate, paramMap);
            if (logger.isInfoEnabled()) {
                logger.info("执行SQL为:" + sql);
                logger.info("操作类型为:" + restSetting.getOperationType());
                logger.info("数据源为:" + restSetting.getDatasource());
            }
            String excutorType = "1";
            try {
                PropertiesFileUtil p = PropertiesFileUtil.getInstance("config");
                excutorType = p.get("rest.client.excutor") == null ? "1" : p.get("rest.client.excutor");
            } catch (Exception e) {
                logger.info("读取rest.client.excutor失败，将使用默认SQL执行器");
            }
            if ("2".equals(excutorType)) {
                logger.info("使用jdbc直连方式执行SQL");
                return excutor2(sql, restSetting.getOperationType(), restSetting.getDatasource());
            } else {
                logger.info("使用应用数据源配置执行SQL");
                return excutor1(sql, restSetting.getOperationType(), restSetting.getDatasource());
            }
        }
    }

    /**
     * sql执行(模式一)
     *
     * @param sql
     * @param operation_type
     * @return
     */
    public Object excutor1(String sql, String operation_type, String datasource) {
        logger.info("开始执行SQL");
        long begin = System.currentTimeMillis();
        DynamicDataSource.setDataSource(datasource);
        Object result;
        try {
            if (OprationTypeEnum.SELECT_LIST.getName().equals(operation_type)) {
                result = restClientCommonExcutorMapper.select(sql);
            } else if (OprationTypeEnum.SELECT_ONE.getName().equals(operation_type)) {
                List list = restClientCommonExcutorMapper.select(sql);
                if (list != null && list.size() > 0) {
                    result = list.get(0);
                } else {
                    result = new HashMap();
                }
            } else if (OprationTypeEnum.UPDATE.getName().equals(operation_type)) {
                result = restClientCommonExcutorMapper.update(sql);
            } else if (OprationTypeEnum.INSERT.getName().equals(operation_type)) {
                result = restClientCommonExcutorMapper.insert(sql);
            } else if (OprationTypeEnum.DELETE.getName().equals(operation_type)) {
                result = restClientCommonExcutorMapper.delete(sql);
            } else {
                throw new RuntimeException("不识别的操作类型：" + operation_type);
            }
        } catch (Exception e) {
            logger.error("sql执行出错", e);
            throw new RuntimeException(e);
        } finally {
            DynamicDataSource.clearDataSource();
        }
        long end = System.currentTimeMillis();
        logger.info("SQL执行结束，用时:"+(end-begin)+"毫秒");
        return result;
    }

    /**
     * sql执行(模式二)
     *
     * @param sql
     * @param operation_type
     * @return
     */
    public Object excutor2(String sql, String operation_type, String datasource) {
        long begin = System.currentTimeMillis();
        RestDatasourceExample restDatasourceExample = new RestDatasourceExample();
        restDatasourceExample.createCriteria().andDsIdEqualTo(datasource);
        RestDatasource restDatasource = restClientDatasourceService.selectFirstByExample(restDatasourceExample);
        if (restDatasource == null) {
            throw new RuntimeException("没有id为:" + datasource + "的数据源");
        }
        String password;
        try {
            password = AesUtils.decode(restDatasource.getDsPassword());
        } catch (Exception e) {
            logger.error("密码解码异常", e);
            return new BaseResult(0, "数据源测试失败", "密码解码异常");
        }
        DatasourceUtil datasourceUtil = new DatasourceUtil(
                restDatasource.getDsType(),
                restDatasource.getDsIp(),
                restDatasource.getDsPort(),
                restDatasource.getDsDatabase(),
                restDatasource.getDsUser(),
                password
        );
        logger.info("开始执行SQL");
        Object result;
        try {
            if (OprationTypeEnum.SELECT_LIST.getName().equals(operation_type)) {
                result = datasourceUtil.selectByParams(sql, new ArrayList());
            } else if (OprationTypeEnum.SELECT_ONE.getName().equals(operation_type)) {
                List list = datasourceUtil.selectByParams(sql, new ArrayList());
                if (list != null && list.size() > 0) {
                    result = list.get(0);
                } else {
                    result = new HashMap();
                }
            } else if (OprationTypeEnum.UPDATE.getName().equals(operation_type)) {
                result = datasourceUtil.updateByParams(sql, new ArrayList());
            } else if (OprationTypeEnum.INSERT.getName().equals(operation_type)) {
                result = datasourceUtil.updateByParams(sql, new ArrayList());
            } else if (OprationTypeEnum.DELETE.getName().equals(operation_type)) {
                result = datasourceUtil.updateByParams(sql, new ArrayList());
            } else {
                throw new RuntimeException("不识别的操作类型：" + operation_type);
            }
        } catch (Exception e) {
            logger.error("sql执行出错", e);
            throw new RuntimeException(e);
        } finally {
            datasourceUtil.release();
        }
        long end = System.currentTimeMillis();
        logger.info("SQL执行结束，用时:"+(end-begin)+"毫秒");
        return result;
    }
}
