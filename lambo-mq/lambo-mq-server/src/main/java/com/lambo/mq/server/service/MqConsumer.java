package com.lambo.mq.server.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lambo.common.annotation.LogAround;
import com.lambo.mq.client.MQConsumerUtil;
import com.lambo.upms.client.dao.model.UpmsLog;
import com.lambo.upms.client.service.api.UpmsClientApiService;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * MQ消费者
 * @author sunzhen
 */
@Component
public class MqConsumer {

    private static Logger logger = LoggerFactory.getLogger(MqConsumer.class);

    @Autowired
    UpmsClientApiService upmsClientApiService;

    /**
     * 消费日志信息
     */
    @PostConstruct
    public void consumeLog() {
        if(logger.isInfoEnabled()){
            logger.info("消费LamboDemo日志信息已启动");
        }
        DefaultMQPushConsumer consumer = MQConsumerUtil.initComsumer();
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        try {
            consumer.subscribe("LamboDemo", "AppLog");
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    for (MessageExt message : msgs) {
                        try{
                            String content = new String(message.getBody(),RemotingHelper.DEFAULT_CHARSET);
                            logger.info(content);
                            UpmsLog upmsLog = JSON.parseObject(content, new TypeReference<UpmsLog>() {});
                            upmsClientApiService.insertUpmsLogSelective(upmsLog);
                        }catch(Exception e){
                            logger.error("解析MQ消息出错",e);
                            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                        }
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
        } catch (Exception e) {
            logger.error("消费日志消息失败", e);
        }

    }


}
