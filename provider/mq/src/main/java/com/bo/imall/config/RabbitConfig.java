package com.bo.imall.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * rabbit config
 *
 * @author HuJianbo
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue defaultQueue() {
        return new Queue("default");
    }

}
