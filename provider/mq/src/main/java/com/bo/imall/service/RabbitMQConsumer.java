package com.bo.imall.service;

/**
 * @Author: PearsonBo
 * @Date: 2019-05-22 18:19
 */
public interface RabbitMQConsumer extends MQConsumer {

    /**
     * 实现消费细节
     *
     * @param json
     */
    @Override
    void consumer(String json);
}
