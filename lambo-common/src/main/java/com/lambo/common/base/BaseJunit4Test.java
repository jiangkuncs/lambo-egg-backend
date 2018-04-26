package com.lambo.common.base;

import com.lambo.common.util.ResourceUtil;
import com.lambo.common.util.SpringContextUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.core.io.Resource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        "classpath*:applicationContext-listener.xml"
})
@Rollback(value=true)
@Transactional
public class BaseJunit4Test extends AbstractTransactionalJUnit4SpringContextTests {

    @Before
    public void setUp() throws Exception {
        Resource[] resources = ResourceUtil.getResources("/sql/*.sql");

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

}
