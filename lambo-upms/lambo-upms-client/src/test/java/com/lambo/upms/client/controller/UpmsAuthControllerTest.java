package com.lambo.upms.client.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lambo.common.base.BaseJunit4Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpmsAuthControllerTest extends BaseJunit4Test {

    @Autowired
    private UpmsAuthController upmsAuthController;
    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(upmsAuthController).build();
    }

    @Test
    public void logout() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(
                MockMvcRequestBuilders.get("/logout")
                        .session(getLoginSession())
        );
        String result =  resultActions.andReturn().getResponse().getContentAsString();
        JSONObject obj = JSON.parseObject(result);
        Assert.assertEquals(obj.getString("code"),"1");
    }

    /**
     * 获取登入信息session
     * @return
     * @throws Exception
     */
    private MockHttpSession getLoginSession() throws Exception{
        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/login")
                        .param("username", "admin")
                        .param("password", "123456"))
                .andExpect(status().isOk())
                .andReturn();
        return (MockHttpSession)result.getRequest().getSession();
    }

}