package com.hbh.demo.rabbitmq.client;

import com.alibaba.fastjson.JSON;
import com.hbh.demo.rabbitmq.base.ConstantValue;
import com.hbh.demo.rabbitmq.util.SpringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MqSendUtil {

    private static final String AMQP_TEMPLATE = "amqpTemplate";

    @Autowired
    private static AmqpTemplate amqpTemplate = SpringUtil.getBean("amqpTemplate",AmqpTemplate.class);

    public static String getDefaultExchange() {
        return ConstantValue.MQ_EXCHANGE;
    }

    public static void sendMessage(String exChange, String queueName, String message) {
        if (StringUtils.isEmpty(exChange)) {
            exChange = getDefaultExchange();
        }
        queueName = ConstantValue.MQ_QUEUE_NAME_PREFIX + "." + queueName;
        String logHead = "MQ发送消息[exChange:" + exChange + ",queueName:" + queueName + ",message:" + message + "]:";
        if (StringUtils.isEmpty(message) || StringUtils.isEmpty(queueName)) {
            throw new RuntimeException(logHead + "消息或队列名称为空!");
        }
        try {
            amqpTemplate.convertAndSend(exChange, queueName, message);
            System.out.println(logHead + "发送成功!");
        } catch (AmqpException e) {
            System.out.println(logHead + "发送异常!");
            throw new RuntimeException(logHead + "发送异常!异常原因:" + e.getMessage());
        }
    }

    public static void sendMessage(String queueName, Object message) {
        sendMessage(queueName, JSON.toJSONString(message));
    }

    public static void sendMessage(String queueName, String message) {
        sendMessage(getDefaultExchange(), queueName, message);
    }

    public static void sendMessage(List<MqSendVo> mqSendVoList) {
        if (CollectionUtils.isEmpty(mqSendVoList)) {
            return;
        }
        for (MqSendVo mqSendVo : mqSendVoList) {
            sendMessage(mqSendVo.getExChangeName(), mqSendVo.getQueueName(), mqSendVo.getMessage());
        }
    }

    public static void sendMessage(MqSendVo mqSendVo) {
        sendMessage(mqSendVo.getExChangeName(), mqSendVo.getQueueName(), mqSendVo.getMessage());
    }
}
