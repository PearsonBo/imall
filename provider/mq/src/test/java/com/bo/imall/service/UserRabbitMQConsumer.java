package com.bo.imall.service;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: PearsonBo
 * @Date: 2019-05-23 13:32
 */
@RabbitListener(queues = "test")
@Component
public class UserRabbitMQConsumer implements RabbitMQConsumer {

    @Override
    @RabbitHandler
    public void consumer(String json) {
        try {
            System.out.println("user.name:" + JSON.parse(json, User.class).getName());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
