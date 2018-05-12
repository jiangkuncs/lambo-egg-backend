package com.lambo.rest.client.factory;

import com.lambo.rest.client.factory.sqlformat.AnalyzeContext;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SqlFormatTest {
    @Test
    public void analyze() throws Exception {
        String sql = "select * from dual where id = ? AND name = ? and phone in (?,?,?) AND ADDRESS NOT IN(?,?)";
        String params = "1000,BILL,15112345678,13112345678,15312345678,BEIJING,SHANGHAI";
        String[] paramArr = params.split(",");
        List paramList = new ArrayList();
        for(int i=0;i<paramArr.length;i++){
            paramList.add(paramArr[i]);
        }
        String result = AnalyzeContext.getContext().analyze(sql,paramList);
        System.out.println(result);
        Assert.assertEquals(result.trim(),"select * from dual where id = 1000 AND name =  'BILL'  and phone in (15112345678,13112345678,15312345678) AND ADDRESS NOT IN( 'BEIJING' , 'SHANGHAI' )");
    }

}