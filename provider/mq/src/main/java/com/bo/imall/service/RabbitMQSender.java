package com.bo.imall.service;

/**
 * @Author: PearsonBo
 * @Date: 2019-05-22 18:16
 */
public interface RabbitMQSender<T> extends MQSender<T> {

    /**
     * 发送信息
     *
     * @param t
     */
    @Override
    void send(T t);
}
