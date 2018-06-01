package com.lambo.mq.client.dao.api;

import com.lambo.mq.client.model.MqCompensate;
import com.lambo.mq.client.model.MqCompensateExample;
import java.util.List;

public interface MqCompensateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MqCompensate record);

    int insertSelective(MqCompensate record);

    List<MqCompensate> selectByExampleWithBLOBs(MqCompensateExample example);

    List<MqCompensate> selectByExample(MqCompensateExample example);

    MqCompensate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MqCompensate record);

    int updateByPrimaryKeyWithBLOBs(MqCompensate record);

    int updateByPrimaryKey(MqCompensate record);
}