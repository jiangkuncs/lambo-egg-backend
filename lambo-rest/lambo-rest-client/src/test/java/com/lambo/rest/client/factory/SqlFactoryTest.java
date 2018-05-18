package com.lambo.rest.client.factory;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class SqlFactoryTest {

    @Test
    public void generateSql1() throws Exception {
        String sqlTemplate = "select a from b where a='${com_id}'";

        Map paramMap = new HashMap<String , Object>(){{
            put("com_id", "123");
        }};

        String result  = SqlFactory.generateSql(sqlTemplate,paramMap);
        Assert.assertEquals(result.trim(),"select a from b where a='123'");

    }

    @Test
    public void generateSql2() throws Exception {
        String sqlTemplate = "select a from b where name like #{@com.lambo.rest.client.factory.Utils@convertLike(name)}";

        Map paramMap = new HashMap<String , Object>(){{
            put("name", "123");
        }};

        String result = SqlFactory.generateSql(sqlTemplate,paramMap);
        Assert.assertEquals(result.trim(),"select a from b where name like  '%123%'");
    }

    @Test
    public void generateSql2_1() throws Exception {
        String sqlTemplate = "select a from b where name like '${'%'+name+'%'}'";

        Map paramMap = new HashMap<String , Object>(){{
            put("name", "123");
        }};

        String result = SqlFactory.generateSql(sqlTemplate,paramMap);
        Assert.assertEquals(result.trim(),"select a from b where name like '%123%'");
    }


    @Test
    public void generateSql3() throws Exception {
        String sqlTemplate = "select a from b <where> <if test = 'com_id != null'> a=#{com_id} </if> </where>";

        Map paramMap = new HashMap<String , Object>(){{
            put("com_id", "123");
        }};

        String result = SqlFactory.generateSql(sqlTemplate,paramMap);
        Assert.assertEquals(result.trim(),"select a from b  WHERE a= '123'");
    }

    @Test
    public void generateSql4() throws Exception {
        String sqlTemplate = "select a from t <where> <if test = 'com_id != null'> a=#{com_id}, </if> <if test = 'date != null'> b=#{date} </if> </where>";

        Map paramMap = new HashMap<String , Object>(){{
            put("date", "20180510");
        }};

        String result = SqlFactory.generateSql(sqlTemplate,paramMap);
        Assert.assertEquals(result.trim(),"select a from t  WHERE b= '20180510'");
    }

    @Test
    public void generateSql5() throws Exception {
        String sqlTemplate = "select a from b where month = #{@com.lambo.rest.client.factory.Utils@getSamePeriod(month)}";

        Map paramMap = new HashMap<String , Object>(){{
            put("month", "201805");
        }};

        String result = SqlFactory.generateSql(sqlTemplate,paramMap);
        Assert.assertEquals(result.trim(),"select a from b where month =  '201705'");
    }

    @Test
    public void generateSql6() throws Exception {
        String sqlTemplate = "select a from b where month = #{BUILTIN_NOW_DATE_YYYYMM}";


        String result = SqlFactory.generateSql(sqlTemplate,new HashMap());
        Assert.assertEquals(result.trim(),"select a from b where month =  '201805'");
    }



}