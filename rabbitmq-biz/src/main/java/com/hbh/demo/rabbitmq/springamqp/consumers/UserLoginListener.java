package com.hbh.demo.rabbitmq.springamqp.consumers;

import com.alibaba.fastjson.JSON;
import com.hbh.demo.rabbitmq.vo.user.UserLoginVO;
import com.rabbitmq.client.Channel;
import org.apache.commons.lang.CharEncoding;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import java.io.UnsupportedEncodingException;

public class UserLoginListener implements ChannelAwareMessageListener {

    public void onMessage(Message message, Channel channel) throws Exception {
        String jsonStr = new String(message.getBody(), CharEncoding.UTF_8);
        System.out.println("B收到消息:"+jsonStr);
        UserLoginVO userLoginVO = JSON.parseObject(jsonStr, UserLoginVO.class);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    public void onMessage(Message message) {
        String jsonStr = null;
        try {
            jsonStr = new String(message.getBody(), CharEncoding.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("收到消息:"+jsonStr);
        UserLoginVO userLoginVO = JSON.parseObject(jsonStr, UserLoginVO.class);
    }
}