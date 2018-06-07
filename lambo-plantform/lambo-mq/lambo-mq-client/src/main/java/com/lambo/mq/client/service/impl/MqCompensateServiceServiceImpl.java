package com.lambo.mq.client.service.impl;

import com.lambo.common.annotation.BaseService;
import com.lambo.common.base.BaseServiceImpl;
import com.lambo.mq.client.dao.api.MqCompensateMapper;
import com.lambo.mq.client.model.MqCompensate;
import com.lambo.mq.client.model.MqCompensateExample;
import com.lambo.mq.client.service.api.MqCompensateService;
import org.springframework.stereotype.Service;

/**
 * MqCompensateServiceService实现
 *
 */
@Service("mqCompensateService")
@BaseService
public class MqCompensateServiceServiceImpl extends BaseServiceImpl<MqCompensateMapper, MqCompensate, MqCompensateExample> implements MqCompensateService {
}
