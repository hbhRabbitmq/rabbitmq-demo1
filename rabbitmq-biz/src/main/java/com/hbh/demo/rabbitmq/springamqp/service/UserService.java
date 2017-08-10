package com.hbh.demo.rabbitmq.springamqp.service;


import com.hbh.demo.rabbitmq.vo.user.UserLoginVO;

public interface UserService {
    String login(UserLoginVO userVO);
}