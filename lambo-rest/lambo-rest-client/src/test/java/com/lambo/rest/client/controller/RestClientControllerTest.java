package com.lambo.rest.client.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:applicationContext.xml",
        "classpath*:applicationContext-jdbc.xml",
        "classpath*:applicationContext-listener.xml",
        "classpath*:springMVC-servlet.xml"
})
@WebAppConfiguration
public class RestClientControllerTest {
    @Autowired
    private RestClientController restClientController;
    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(restClientController).build();
    }

    @Test
    public void query() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(
                MockMvcRequestBuilders.get("/rest/service/aaa/bbb?t=1")
        );
        String result =  resultActions.andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }


}