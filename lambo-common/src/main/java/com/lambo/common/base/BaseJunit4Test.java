package com.lambo.common.base;

import com.lambo.common.utils.io.ResourceUtil;
import com.lambo.common.utils.spring.SpringContextUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.core.io.Resource;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @ClassName BaseJunit4Test
 * @Descirption TODO
 * @Author sunzhen
 * @Date 2018/4/25 15:25
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:applicationContext.xml",
        "classpath*:applicationContext-jdbc.xml",
        "classpath*:applicationContext-listener.xml",
        "classpath*:applicationContext-shiro.xml",
        "classpath*:springMVC-servlet.xml"
})
@WebAppConfiguration
public class BaseJunit4Test extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private SecurityManager securityManager;

    @Before
    public void initData() throws Exception {
        Resource[] resources = null;
        try{
            resources = ResourceUtil.getResources("/sql/*.sql");
        }catch(Exception e){
            //
        }
        if(resources == null || resources.length == 0){
            return ;
        }
        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringContextUtil.getBean("sqlSessionFactory");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Connection conn = sqlSession.getConnection();
        Statement state = null;
        ResultSet rs = null;
        try {
            state = conn.createStatement();
            for(int i=0;i<resources.length;i++){
                state.execute("runscript from '" + resources[i].getURI().toString().substring(6) + "'");
            }

        } catch (Exception e) {
            System.out.println("执行数据库初始化脚本出错：" + e);
            e.printStackTrace();
        } finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (state != null) {
                try {
                    state.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    state.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    @Before
    public void initShiro(){
        SecurityUtils.setSecurityManager(securityManager);
    }


}
