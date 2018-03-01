package com.lambo.ndp.dao;

import java.util.List;
import java.util.Map;

public interface DemoUserOldMapper {
    //老lambo
    int insertUser(Map<String, Object> param);

    int updateUser(Map<String, Object> param);

    int deleteUser(Integer id);

    List<Map<String,Object>> queryUser(Map<String, Object> param);

    int countUser(Map<String, Object> param);

    Map<String,Object> selectUserById(Integer id);
}