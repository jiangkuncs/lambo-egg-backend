package com.lambo.ndp.service;

import java.util.List;
import java.util.Map;

/**
* DemoUserService接口
* Created by lambo on 2017/11/14.
*/
public interface DemoUserService {

    public int insertUser(Map<String, Object> param);

    public int updateUser(Map<String, Object> param);

    public int deleteUser(String ids);

    public List<Map<String,Object>> queryUser(Map<String, Object> param);

    public int countUser(Map<String, Object> param);

    public Map<String,Object> selectUserById(Integer id);

}