package com.lambo.auth.client.service.api;


import com.alibaba.fastjson.JSONArray;

/**
 * 回话信息接口
 * @author sunzhen
 */
public interface AuthClientApiService {

    /**
     * 获取当前登录用户ID
     * @return
     */
    public String getUserId();

    /**
     * 获取当前登录用户公司
     * @return
     */
    public String getComId();

    /**
     * 获取当前登录用户的用户名
     * @return
     */
    public String getUserName();

    /**
     * 获取当前登录用户的数据权限
     * @return
     */
    public JSONArray getDataPermissions();
}
