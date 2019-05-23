package com.bo.imall.service;

/**
 * 生产者
 *
 * @Author: PearsonBo
 * @Date: 2019-05-22 18:01
 */
public interface MQSender<T> {

    /**
     * 生产
     *
     * @param t
     */
    void send(T t);

}
