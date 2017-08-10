package com.hbh.demo.rabbitmq.vo.user;

import com.hbh.demo.rabbitmq.base.MqConstant;
import com.hbh.demo.rabbitmq.client.MqSendVo;
import com.hbh.demo.rabbitmq.vo.BaseVO;
import lombok.Data;

@Data
public class UserLoginVO extends BaseVO {
    private String userName ;
    private String password ;

    public UserLoginVO(){

    }
    public UserLoginVO(String userName,String password){
        this.userName = userName;
        this.password = password;
    }
    public static MqSendVo buildMqSendVo(UserLoginVO userLoginVO){
        return new MqSendVo(MqConstant.MQ_ROUTING_KEY_USER_LOGIN,userLoginVO);
    }
}