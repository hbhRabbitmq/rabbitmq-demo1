package com.hbh.demo.rabbitmq.springamqp.controller;

import com.hbh.demo.rabbitmq.springamqp.service.UserService;
import com.hbh.demo.rabbitmq.vo.user.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.CountDownLatch;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public String login(UserLoginVO userLoginVO) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(30);
        for (int i = 0; i < 30; i++) {
            final UserLoginVO userLoginVO1 = new UserLoginVO("username" + i, "password" + i);
            new Thread(new Runnable() {
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while (true){
                        userService.login(userLoginVO1);
                    }
                }
            }).start();
            Thread.sleep(500);
            latch.countDown();
        }
        latch.await();
        return "已发送";
    }
}