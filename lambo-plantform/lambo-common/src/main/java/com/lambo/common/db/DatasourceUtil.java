package com.lambo.common.db;

import com.lambo.common.utils.other.JdbcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DatasourceUtil
 * @Descirption 数据连接适配器,驱动需要另外引入
 * @Author sunzhen
 * @Date 2018/5/29 8:55
 **/
public class DatasourceUtil {
    private final static Logger logger = LoggerFactory.getLogger(DatasourceUtil.class);

    public static String DB_TYPE_MYSQL = "mysql";
    public static String DB_TYPE_GREENPLUM = "greenplum";
    public static String DB_TYPE_DB2 = "db2";
    public static String DB_TYPE_ORACLE = "oracle";

    private JdbcUtil jdbcUtil = null;

    private String _dbType = null;


    public DatasourceUtil(String dbType, String ip, int port, String dbname, String username, String password) {

        String driver, url;
        if (DB_TYPE_MYSQL.equals(dbType)) {
            driver = "com.mysql.jdbc.Driver";
            url = "jdbc:mysql://" + ip + ":" + port + "/" + dbname + "?useUnicode=true&characterEncoding=utf-8";
        } else if (DB_TYPE_GREENPLUM.equals(dbType)) {
            driver = "org.postgresql.Driver";
            url = "jdbc:postgresql://" + ip + ":" + port + "/" + dbname;
        } else if (DB_TYPE_DB2.equals(dbType)) {
            driver = "com.ibm.db2.jcc.DB2Driver";
            url = "jdbc:db2://" + ip + ":" + port + "/" + dbname;
        } else if (DB_TYPE_ORACLE.equals(dbType)) {
            driver = "oracle.jdbc.driver.OracleDriver";
            url = "jdbc:oracle:thin:@" + ip + ":" + port + ":" + dbname;
        } else {
            throw new RuntimeException("不支持的数据库类型：" + dbType);
        }
        _dbType = dbType;
        jdbcUtil = new JdbcUtil(driver, url, username, password);

    }

    public boolean testConnection() {
        String testSql = "";
        if (_dbType.equals(DB_TYPE_MYSQL)) {
            testSql = "select 1";
        } else if (_dbType.equals(DB_TYPE_GREENPLUM)) {
            testSql = "select 1";
        } else if (_dbType.equals(DB_TYPE_DB2)) {
            testSql = "select 1 from SYSIBM.SYSDUMMY1";
        } else if (_dbType.equals(DB_TYPE_ORACLE)) {
            testSql = "SELECT 1 FROM DUAL";
        }
        try {
            List list = jdbcUtil.selectByParams(testSql, new ArrayList());
            if (list != null && list.size() > 0) {
                return true;
            }
        } catch (Exception e) {
            logger.error("数据源测试失败", e);
        }
        return false;
    }

    /**
     * 更新数据
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public boolean updateByParams(String sql, List params) throws SQLException {
        return jdbcUtil.updateByParams(sql,params);
    }

    /**
     * 查询多条记录
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public List<Map> selectByParams(String sql, List params) throws SQLException {
        return jdbcUtil.selectByParams(sql,params);
    }

    /**
     * 释放连接
     */
    public void release() {
        jdbcUtil.release();
    }

}
