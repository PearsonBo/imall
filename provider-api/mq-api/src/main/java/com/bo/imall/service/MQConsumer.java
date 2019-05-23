package com.bo.imall.service;

/**
 * 消费者
 *
 * @Author: PearsonBo
 * @Date: 2019-05-22 18:01
 */
public interface MQConsumer {


    /**
     * 消费
     *
     * @param json
     * @return
     */
    void consumer(String json);
}
