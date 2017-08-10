package com.hbh.demo.rabbitmq.client;

import com.alibaba.fastjson.JSON;
import lombok.Data;

@Data
public class MqSendVo {

    private String exChangeName;
    private String queueName;
    private String message;

    public MqSendVo(String exChangeName, String queueName, String message) {
        this.exChangeName = exChangeName;
        this.queueName = queueName;
        this.message = message;
    }

    public MqSendVo(String exChangeName, String queueName, Object message) {
        this(exChangeName, queueName, JSON.toJSONString(message));
    }

    public MqSendVo(String queueName, String message) {
        this(MqSendUtil.getDefaultExchange(), queueName, message);
    }

    public MqSendVo(String queueName, Object message) {
        this(MqSendUtil.getDefaultExchange(), queueName, JSON.toJSONString(message));
    }
}