package com.hbh.demo.rabbitmq.springamqp.service.impl;

import com.hbh.demo.rabbitmq.client.MqSendUtil;
import com.hbh.demo.rabbitmq.springamqp.service.UserService;
import com.hbh.demo.rabbitmq.vo.user.UserLoginVO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    public String login(UserLoginVO userVO) {
        MqSendUtil.sendMessage(UserLoginVO.buildMqSendVo(userVO));
        return "";
    }
}