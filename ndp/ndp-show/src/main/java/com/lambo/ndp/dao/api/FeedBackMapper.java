package com.lambo.ndp.dao.api;

import com.lambo.ndp.model.FeedBack;
import com.lambo.ndp.model.FeedBackExample;
import java.util.List;

public interface FeedBackMapper {
    int deleteByPrimaryKey(Integer recordid);

    int insert(FeedBack record);

    int insertSelective(FeedBack record);

    List<FeedBack> selectByExampleWithBLOBs(FeedBackExample example);

    List<FeedBack> selectByExample(FeedBackExample example);

    FeedBack selectByPrimaryKey(Integer recordid);

    int updateByPrimaryKeySelective(FeedBack record);

    int updateByPrimaryKeyWithBLOBs(FeedBack record);

    int updateByPrimaryKey(FeedBack record);
}