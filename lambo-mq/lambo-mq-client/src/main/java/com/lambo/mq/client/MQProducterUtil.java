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
 * @ClassName MQProducterUtil
 * @Descirption RocketMQ生产者相关工具类
 * @Author sunzhen
 * @Date 2018/5/31 9:11
 **/
public class MQProducterUtil {

    private static Logger logger = LoggerFactory.getLogger(MQProducterUtil.class);

    private static MqCompensateService mqCompensateService = (MqCompensateService) SpringContextUtil.getBean("mqCompensateService");

    private static Boolean mqUse = false;

    private static DefaultMQProducer producer = null;


    public static boolean mqInUse(){
        if(mqUse == null){
            try {
                mqUse = Boolean.valueOf(PropertiesFileUtil.getInstance("MQproductor").get("mq.use"));
            } catch (Exception e) {
                logger.info("获取MQproductor配置文件异常,将不启用MQ");
            }
        }
        return mqUse;
    }

    /**
     * 创建生产者
     *
     * @return
     */
    public static DefaultMQProducer getProducer() {
        if (producer == null  ) {
            String nameServerAddr = null, producerGroup = null;
            try {
                nameServerAddr = PropertiesFileUtil.getInstance("MQproductor").get("nameServer.addr");
                producerGroup = PropertiesFileUtil.getInstance("MQproductor").get("group.name");
            } catch (Exception e) {
                logger.error("获取MQproductor配置文件异常", e);
            }
            producer = new DefaultMQProducer(producerGroup);
            producer.setNamesrvAddr(nameServerAddr);
            try {
                producer.start();
            } catch (Exception e) {
                logger.error("启动MQproductor生产者异常", e);
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
        if (msg == null) {
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
        if (msg == null) {
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
        if (msg == null) {
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
            topic = PropertiesFileUtil.getInstance("MQproductor").get("message.topic");
        } catch (Exception e) {
            logger.error("获取MQproductor配置文件异常", e);
        }
        byte[] msgByte = null;
        try {
            msgByte = message.getBytes(RemotingHelper.DEFAULT_CHARSET);
        } catch (Exception e) {
            logger.error("message=" + message);
            logger.error("组织MQproductor信息体异常", e);
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
}
