package com.hbh.demo.rabbitmq.springamqp.controller;

import com.hbh.demo.rabbitmq.springamqp.service.UserService;
import com.hbh.demo.rabbitmq.vo.user.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public String login(UserLoginVO userLoginVO){
        for (int i= 0 ;i<1000;i++){
            UserLoginVO userLoginVO1 = new UserLoginVO("username"+i,"password"+i);
            userService.login(userLoginVO1);
        }
        return "已发送";
    }



}