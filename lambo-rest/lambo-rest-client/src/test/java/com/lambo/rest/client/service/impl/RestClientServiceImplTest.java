package com.lambo.rest.client.service.impl;

import com.lambo.common.base.BaseJunit4Test;
import com.lambo.common.db.DynamicDataSource;
import com.lambo.rest.client.constant.OprationTypeEnum;
import com.lambo.rest.client.model.RestSetting;
import com.lambo.rest.client.service.api.RestClientService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class RestClientServiceImplTest extends BaseJunit4Test {

    @Autowired
    RestClientService restClientService;

//    @Test
//    public void getResult() throws Exception {
//        RestSetting restSetting = new RestSetting();
//        Map paramMap = new HashMap();
//        Boolean mock = true;
//        restClientService.getResult(restSetting,paramMap,mock);
//    }

    /**
     * 测试SELECT_LIST
     * @throws Exception
     */
    @Test
    public void excutor1() throws Exception {
        String sql = "select * from test_data_table";
        List list = (List)restClientService.excutor(sql, OprationTypeEnum.SELECT_LIST.getName(),"masterDataSource");
        Assert.assertTrue(list.size() > 0);
    }

    /**
     * 测试SELECT_ONE
     * @throws Exception
     */
    @Test
    public void excutor2() throws Exception {
        String sql = "select * from test_data_table where id = '1'";
        Map map = (Map)restClientService.excutor(sql, OprationTypeEnum.SELECT_ONE.getName(),"masterDataSource");
        Assert.assertEquals(map.get("ID"),"1");
    }

    /**
     * 测试INSERT
     * @throws Exception
     */
    @Test
    public void excutor3() throws Exception {
        String sql = "INSERT INTO `test_data_table` VALUES ('4', 'sun', '1')";
        int count = (int)restClientService.excutor(sql, OprationTypeEnum.INSERT.getName(),"masterDataSource");
        Assert.assertEquals(count,1);
    }

    /**
     * 测试UPDATE
     * @throws Exception
     */
    @Test
    public void excutor4() throws Exception {
        String sql = "update test_data_table set name ='123' where id ='1'";
        int count = (int)restClientService.excutor(sql, OprationTypeEnum.UPDATE.getName(),"masterDataSource");
        Assert.assertEquals(count,1);
    }

    /**
     * 测试DELETE
     * @throws Exception
     */
    @Test
    public void excutor5() throws Exception {
        String sql = "delete from test_data_table where id ='1'";
        int count = (int)restClientService.excutor(sql, OprationTypeEnum.DELETE.getName(),"masterDataSource");
        Assert.assertEquals(count,1);
    }

    /**
     * 测试explain
     * @throws Exception
     */
    @Test
    public void excutor6() throws Exception {
        String sql = "explain select * from test_data_table";
        Object object = restClientService.excutor(sql, OprationTypeEnum.SELECT_LIST.getName(),"masterDataSource");
        System.out.println(object);
    }


}