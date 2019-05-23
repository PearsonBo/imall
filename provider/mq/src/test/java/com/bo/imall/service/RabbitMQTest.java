package com.bo.imall.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: PearsonBo
 * @Date: 2019-05-22 18:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQTest {

    @Autowired
    private UserRabbitMQSender sender;


    @Bean
    public Queue testQueue() {
        return new Queue("test");
    }

    @Test
    public void test() {
        User user = new User();
        user.setName("nnn");
        sender.setQueue("test");
        this.sender.send(user);
    }
}
