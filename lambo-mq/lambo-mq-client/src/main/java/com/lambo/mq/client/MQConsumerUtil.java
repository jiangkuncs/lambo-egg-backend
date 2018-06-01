package com.lambo.mq.client;

import com.lambo.common.utils.io.PropertiesFileUtil;
import com.lambo.common.utils.spring.SpringContextUtil;
import com.lambo.mq.client.model.MqCompensate;
import com.lambo.mq.client.service.api.MqCompensateService;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @ClassName RocketMQUtil
 * @Descirption RocketMQ相关工具类
 * @Author sunzhen
 * @Date 2018/5/31 9:11
 **/
public class MQConsumerUtil {

    private static Logger logger = LoggerFactory.getLogger(MQConsumerUtil.class);

    private static MqCompensateService mqCompensateService = (MqCompensateService) SpringContextUtil.getBean("mqCompensateService");

    private static Boolean mqUse = false;

    private static DefaultMQProducer producer = null;

    /**
     * 创建生产者
     *
     * @return
     */
    public static DefaultMQProducer getProducer() {
        try {
            mqUse = Boolean.valueOf(PropertiesFileUtil.getInstance("rocketMQ").get("mq.use"));
        } catch (Exception e) {
            logger.error("获取rocketMQ配置文件异常", e);
        }
        if (mqUse && producer == null  ) {
            String nameServerAddr = null, producerGroup = null;
            try {
                nameServerAddr = PropertiesFileUtil.getInstance("rocketMQ").get("nameServer.addr");
                producerGroup = PropertiesFileUtil.getInstance("rocketMQ").get("producerGroup.name");
            } catch (Exception e) {
                logger.error("获取rocketMQ配置文件异常", e);
            }
            producer = new DefaultMQProducer(producerGroup);
            producer.setNamesrvAddr(nameServerAddr);
            try {
                producer.start();
            } catch (Exception e) {
                logger.error("启动rocketMQ生产者异常", e);
            }
        }

        return producer;
    }

    /**
     * 发送消息(有补偿机制)
     *
     * @param msg
     * @throws InterruptedException
     * @throws RemotingException
     * @throws MQClientException
     * @throws MQBrokerException
     */
    public static void send(Message msg) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        if (!mqUse || msg == null) {
            return;
        }
        if (producer == null) {
            getProducer();
        }
        try {
            SendResult sendResult = producer.send(msg);
            if (sendResult.getSendStatus() == SendStatus.SEND_OK) {
                logger.info("消息发送到MQ成功");
            } else {
                logger.error("消息发送到MQ失败：" + sendResult.getSendStatus().name());
                persistenceMessage(msg, sendResult.getSendStatus().name());
            }
        } catch (Exception e) {
            logger.error("消息发送到MQ失败", e);
            persistenceMessage(msg, e.getLocalizedMessage());
        }
    }

    /**
     * 发送消息(支持回调)
     *
     * @param msg
     * @param sendCallback
     * @throws MQClientException
     * @throws RemotingException
     * @throws InterruptedException
     */
    public static void send(Message msg, SendCallback sendCallback) throws MQClientException, RemotingException, InterruptedException {
        if (!mqUse || msg == null) {
            return;
        }
        if (producer == null) {
            getProducer();
        }
        producer.send(msg, sendCallback);

    }

    /**
     * 发送消息（无补偿）
     *
     * @param msg
     * @throws MQClientException
     * @throws RemotingException
     * @throws InterruptedException
     */
    public static void sendOneway(Message msg) throws MQClientException, RemotingException, InterruptedException {
        if (!mqUse || msg == null) {
            return;
        }
        if (producer == null) {
            getProducer();
        }
        producer.sendOneway(msg);
    }

    /**
     * 消息创建工具
     *
     * @param tag
     * @param keys
     * @param message
     * @return
     */
    public static Message geneMessage(String tag, String keys, String message) {
        String topic = null;
        try {
            topic = PropertiesFileUtil.getInstance("rocketMQ").get("message.topic");
        } catch (Exception e) {
            logger.error("获取rocketMQ配置文件异常", e);
        }
        byte[] msgByte = null;
        try {
            msgByte = message.getBytes(RemotingHelper.DEFAULT_CHARSET);
        } catch (Exception e) {
            logger.error("message=" + message);
            logger.error("组织rocketMQ信息体异常", e);
        }
        return new Message(topic, tag, keys, msgByte);
    }

    /**
     * 持久化消息以便之后补偿
     *
     * @param msg
     * @param cause
     */
    private static void persistenceMessage(Message msg, String cause) {
        logger.info("开始持久化消息...");
        try {
            MqCompensate mqCompensate = new MqCompensate();
            mqCompensate.setTopic(msg.getTopic());
            mqCompensate.setTag(msg.getTags());
            mqCompensate.setKeys(msg.getKeys());
            mqCompensate.setMessage(new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET));
            mqCompensate.setCause(cause);
            mqCompensate.setStatus("0");
            mqCompensate.setCreateDate(new Date());
            mqCompensateService.insert(mqCompensate);
        } catch (Exception e) {
            logger.error("持久化消息失败", e);
        }
        logger.info("持久化消息成功");

    }


//    public static void main(String[] args) throws Exception {
//        DefaultMQProducer producer = new DefaultMQProducer("product1");
//        producer.setNamesrvAddr("10.10.10.136:9876");
//        producer.start();
//        for (int i = 0; i < 100; i++) {
//            Message msg = new Message("TopicTest", "TagA", ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
//            SendResult sendResult = producer.send(msg);
//            System.out.printf("%s%n", sendResult);
//        }
//        producer.shutdown();
//    }

//    public static void main(String[] args) throws InterruptedException, MQClientException {
//
//        //声明并初始化一个consumer
//        //需要一个consumer group名字作为构造方法的参数，这里为consumer1
//        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer2");
//
//        //同样也要设置NameServer地址
//        consumer.setNamesrvAddr("10.10.10.136:9876");
//
//        //这里设置的是一个consumer的消费策略
//        //CONSUME_FROM_LAST_OFFSET 默认策略，从该队列最尾开始消费，即跳过历史消息
//        //CONSUME_FROM_FIRST_OFFSET 从队列最开始开始消费，即历史消息（还储存在broker的）全部消费一遍
//        //CONSUME_FROM_TIMESTAMP 从某个时间点开始消费，和setConsumeTimestamp()配合使用，默认是半个小时以前
//        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
//
//        //设置consumer所订阅的Topic和Tag，*代表全部的Tag
//        consumer.subscribe("TopicTest", "*");
//
//        //设置一个Listener，主要进行消息的逻辑处理
//        consumer.registerMessageListener(new MessageListenerConcurrently() {
//
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
//                                                            ConsumeConcurrentlyContext context) {
//
//                System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs);
//
//                //返回消费状态
//                //CONSUME_SUCCESS 消费成功
//                //RECONSUME_LATER 消费失败，需要稍后重新消费
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
//        });
//
//        //调用start()方法启动consumer
//        consumer.start();
//
//        System.out.println("Consumer Started.");
//    }


}
