package com.lambo.rest.manage;

import com.lambo.common.db.DatasourceUtil;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatasourceUtilTest {
    @Test
    public void testMySqlConnection() throws Exception {
        String dbType = "mysql";
        String ip = "10.10.10.136";
        int port = 3306;
        String dbname = "test";
        String username = "root";
        String password = "root";

        DatasourceUtil datasourceUtil = new DatasourceUtil(dbType, ip, port, dbname, username, password);
        System.out.println(datasourceUtil.testConnection());
    }

    @Test
    public void testGreenPlumConnection() throws Exception {
        String dbType = "greenplum";
        String ip = "10.10.10.93";
        int port = 5432;
        String dbname = "yxgkdb";
        String username = "yxgk";
        String password = "123456a!";

        DatasourceUtil datasourceUtil = new DatasourceUtil(dbType, ip, port, dbname, username, password);
        System.out.println(datasourceUtil.testConnection());
    }

    @Test
    public void testDB2Connection() throws Exception {
        String dbType = "db2";
        String ip = "10.110.1.68";
        int port = 50000;
        String dbname = "v6db";
        String username = "db2inst1";
        String password = "db2inst1";

        DatasourceUtil datasourceUtil = new DatasourceUtil(dbType, ip, port, dbname, username, password);
        System.out.println(datasourceUtil.testConnection());
    }

    @Test
    public void testOracleConnection() throws Exception {
        String dbType = "oracle";
        String ip = "10.10.10.43";
        int port = 1521;
        String dbname = "v6db";
        String username = "v6user";
        String password = "inspur69";

        DatasourceUtil datasourceUtil = new DatasourceUtil(dbType, ip, port, dbname, username, password);
        System.out.println(datasourceUtil.testConnection());
    }

}