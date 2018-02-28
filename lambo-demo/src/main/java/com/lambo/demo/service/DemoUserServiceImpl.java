package com.lambo.demo.service;

import com.lambo.demo.dao.DemoUserOldMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* DemoUserService实现
* Created by wangjie on 2017/11/14.
*/
@Service
public class DemoUserServiceImpl implements DemoUserService {

    private static Logger logger = LoggerFactory.getLogger(DemoUserServiceImpl.class);

    @Autowired
    DemoUserOldMapper demoUserOldMapper;

    @Override
    public int insertUser(Map<String,Object> param) {
        return demoUserOldMapper.insertUser(param);
    }

    @Override
    public int updateUser(Map<String,Object> param) {
        return demoUserOldMapper.updateUser(param);
    }

    @Override
    public int deleteUser(String ids) {
        int count = 0;
        if(ids == null || ids.isEmpty()){
            return count;
        }
        String[] userIds = null;
        if(ids.indexOf(",")>-1){
            userIds = ids.split(",");
        }else{
            userIds = new String[]{ids};
        }
        for(int i=0;i<userIds.length;i++){
            count += demoUserOldMapper.deleteUser(Integer.parseInt(userIds[i]));
        }
        return count;
    }

    @Override
    public List<Map<String,Object>> queryUser(Map<String,Object> param) {
        return demoUserOldMapper.queryUser(param);
    }

    @Override
    public int countUser(Map<String,Object> param) {
        return demoUserOldMapper.countUser(param);
    }

    @Override
    public Map<String, Object> selectUserById(Integer id) {
        return demoUserOldMapper.selectUserById(id);
    }
}