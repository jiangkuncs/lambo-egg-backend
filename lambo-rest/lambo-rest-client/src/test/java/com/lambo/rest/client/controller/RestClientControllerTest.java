package com.lambo.rest.client.controller;

import com.alibaba.fastjson.JSONObject;
import com.lambo.common.base.BaseJunit4Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class RestClientControllerTest extends BaseJunit4Test {
    @Autowired
    private RestClientController restClientController;
    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(restClientController).build();
    }

    /**
     * 简单测试
     * @throws Exception
     */
    @Test
    public void query1() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(
                MockMvcRequestBuilders.get("/rest/service/aaa/bbb?id=1")
        );
        String result =  resultActions.andReturn().getResponse().getContentAsString();

        JSONObject obj = JSONObject.parseObject(result);
        Assert.assertTrue(obj.getJSONArray("data").size() > 0);
    }


    /**
     * 简单mock
     * @throws Exception
     */
    @Test
    public void query2() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(
                MockMvcRequestBuilders.get("/rest/service/aaa/bbb?id=1&mock=true")
        );
        String result =  resultActions.andReturn().getResponse().getContentAsString();
        JSONObject obj = JSONObject.parseObject(result);
        Assert.assertTrue(obj.getJSONArray("data").size() > 0);
    }


}