package com.lambo.mq.client;

import com.lambo.common.utils.io.PropertiesFileUtil;
import com.lambo.common.utils.spring.SpringContextUtil;
import com.lambo.mq.client.model.MqCompensate;
import com.lambo.mq.client.service.api.MqCompensateService;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * @ClassName MQConsumerUtil
 * @Descirption RocketMQ消费者相关工具类
 * @Author sunzhen
 * @Date 2018/5/31 9:11
 **/
public class MQConsumerUtil {

    private static Logger logger = LoggerFactory.getLogger(MQConsumerUtil.class);

    /**
     * 创建消费者实例
     * @return
     */
    public static DefaultMQPushConsumer initComsumer(){
        return initComsumer(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
    }

    /**
     * 创建消费者实例
     * @return
     */
    private static DefaultMQPushConsumer initComsumer(ConsumeFromWhere consumeFromWhere){
        DefaultMQPushConsumer consumer = null;
        String nameServerAddr = null, comsumerGroup = null;
        try {
            nameServerAddr = PropertiesFileUtil.getInstance("MQcomsumer").get("nameServer.addr");
            comsumerGroup = PropertiesFileUtil.getInstance("MQcomsumer").get("group.name");
        } catch (Exception e) {
            logger.error("获取MQcomsumer配置文件异常", e);
        }
        consumer = new DefaultMQPushConsumer(comsumerGroup);
        consumer.setNamesrvAddr(nameServerAddr);
        /**
         * 消息拉取的策略
         * CONSUME_FROM_LAST_OFFSET 默认策略，从该队列最尾开始消费，即跳过历史消息
         * CONSUME_FROM_FIRST_OFFSET 从队列最开始开始消费，即历史消息（还储存在broker的）全部消费一遍
         * CONSUME_FROM_TIMESTAMP 从某个时间点开始消费，和setConsumeTimestamp()配合使用，默认是半个小时以前
         * @param consumeFromWhere
         */
        consumer.setConsumeFromWhere(consumeFromWhere);
        return consumer;
    }


    public static void main(String[] args){

    }


}
