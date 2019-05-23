package com.bo.imall.service;

import com.alibaba.dubbo.common.json.JSON;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: PearsonBo
 * @Date: 2019-05-23 16:59
 */
@Component
public class UserRabbitMQSender implements RabbitMQSender<User> {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    private String queue;

    public void setQueue(String queue) {
        this.queue = queue;
    }

    @Override
    public void send(User user) {
        try {
            this.rabbitTemplate.convertAndSend(this.queue, JSON.json(user));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
