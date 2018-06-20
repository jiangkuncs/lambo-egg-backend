package com.lambo.auth.client.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.lambo.auth.client.service.api.AuthClientApiService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

/**
 * @ClassName AuthClientApiServiceImpl
 * @Descirption TODO
 * @author sunzhen
 * @Date 2018/6/19 17:35
 **/
@Service
public class AuthClientApiServiceImpl implements AuthClientApiService {

    /**
     * 获取当前登录用户ID
     * @return
     */
    @Override
    public String getUserId(){
        Session session = SecurityUtils.getSubject().getSession();
        return (String)session.getAttribute("userId");
    }

    /**
     * 获取当前登录用户公司
     * @return
     */
    @Override
    public String getComId(){
        Session session = SecurityUtils.getSubject().getSession();
        return (String)session.getAttribute("comId");
    }

    /**
     * 获取当前登录用户的用户名
     * @return
     */
    @Override
    public String getUserName(){
        Session session = SecurityUtils.getSubject().getSession();
        return (String)session.getAttribute("userName");
    }

    /**
     * 获取当前登录用户的数据权限
     * @return
     */
    @Override
    public JSONArray getDataPermissions(){
        Session session = SecurityUtils.getSubject().getSession();
        return (JSONArray)session.getAttribute("dataPermissions");
    }
}
