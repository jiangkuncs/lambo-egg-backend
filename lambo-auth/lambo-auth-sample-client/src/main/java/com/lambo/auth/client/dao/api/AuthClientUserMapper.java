package com.lambo.auth.client.dao.api;

import com.lambo.auth.client.dao.model.UpmsUser;
import com.lambo.auth.client.dao.model.UpmsUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthClientUserMapper {

    List<UpmsUser> selectByExample(UpmsUserExample example);

    UpmsUser selectByPrimaryKey(Integer userId);


}