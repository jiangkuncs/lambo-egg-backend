package com.lambo.rest.client.factory;

import com.lambo.rest.client.factory.sqltemplate.SqlMeta;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

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
        String sqlTemplate = "select a from b where a=#{com_id}";

        Map paramMap = new HashMap<String , Object>(){{
            put("com_id", "123");
        }};

        String result = SqlFactory.generateSql(sqlTemplate,paramMap);
        Assert.assertEquals(result.trim(),"select a from b where a= '123'");

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



}