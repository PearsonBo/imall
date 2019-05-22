package com.bo.imall.service;import com.bo.imall.config.SystemConfig;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.redis.core.RedisTemplate;import org.springframework.data.redis.core.ValueOperations;import java.io.Serializable;import java.util.Set;import java.util.concurrent.TimeUnit;/** * @Author: PerasonBo * @Date: 2019-05-21 17:29 */@com.alibaba.dubbo.config.annotation.Service(version = SystemConfig.SPI_VERSION)@org.springframework.stereotype.Servicepublic class RedisServiceImpl implements RedisService {    @Autowired    private RedisTemplate redisTemplate;    @Override    public void remove(String... keys) {        for (String key : keys) {            remove(key);        }    }    @Override    public void removePattern(String pattern) {        Set<Serializable> keys = redisTemplate.keys(pattern);        if (keys.size() > 0) {            redisTemplate.delete(keys);        }    }    @Override    public void remove(String key) {        if (exists(key)) {            redisTemplate.delete(key);        }    }    @Override    public boolean exists(String key) {        return redisTemplate.hasKey(key);    }    @Override    public Serializable get(String key) {        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();        return (Serializable) operations.get(key);    }    @Override    public boolean set(String key, Serializable value) {        boolean result = false;        try {            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();            operations.set(key, value);            result = true;        } catch (Exception e) {            e.printStackTrace();        }        return result;    }    @Override    public boolean set(String key, Serializable value, Long expireTime) {        boolean result = false;        try {            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();            operations.set(key, value);            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);            result = true;        } catch (Exception e) {            e.printStackTrace();        }        return result;    }}