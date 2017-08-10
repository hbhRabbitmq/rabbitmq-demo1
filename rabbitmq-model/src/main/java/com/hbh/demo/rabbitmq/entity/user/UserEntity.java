package com.hbh.demo.rabbitmq.entity.user;

import com.hbh.demo.rabbitmq.entity.BaseEntity;
import lombok.Data;

@Data
public class UserEntity  extends BaseEntity{
    private String userName ;
    private String password ;
}