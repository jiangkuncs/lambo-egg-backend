package com.lambo.upms.client.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lambo.common.base.BaseJunit4Test;
import com.lambo.common.utils.io.ResourceUtil;
import com.lambo.common.utils.spring.SpringContextUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:applicationContext.xml",
        "classpath*:applicationContext-jdbc.xml",
        "classpath*:applicationContext-listener.xml",
        "classpath*:applicationContext-shiro.xml",
        "classpath*:springMVC-servlet.xml"
})
@WebAppConfiguration
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
                        //.accept(MediaType.APPLICATION_JSON)
        );
        String result =  resultActions.andReturn().getResponse().getContentAsString();
        JSONObject obj = JSON.parseObject(result);
        System.out.println(obj.toString());
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
                        .param("password", "123456")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        return (MockHttpSession)result.getRequest().getSession();
    }

}