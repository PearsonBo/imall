package com.bo.imall.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RedisReadHelper<T> {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, T> redisObjectTemplate;


    public String getStringInRedis(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void saveStringInRedis(String key, String value) {
        if (key == null || value == null) {
            return;
        }
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public void deleteObject(String... keys) {
        if (keys == null || keys.length == 0) {
            return;
        }
        if (keys.length == 1) {
            redisObjectTemplate.delete(keys[0]);
        } else {
            redisObjectTemplate.delete(Arrays.asList(keys));
        }
    }

    public T getObject(String key) {
        if (key == null) {
            return null;
        }
        return redisObjectTemplate.opsForValue().get(key);
    }

    public void saveObject(String key, T value) {
        if (key == null || value == null) {
            return;
        }

        redisObjectTemplate.opsForValue().set(key, value);
    }

    public List<T> getObjectList(String key) {
        if (key == null) {
            return new ArrayList<>();
        }

        ListOperations<String, T> ops = redisObjectTemplate.opsForList();
        Long size = ops.size(key);

        return ops.range(key, 0, size);
    }

    public void saveObjectInList(String key, T value) {
        if (key == null || value == null) {
            return;
        }
        ListOperations<String, T> ops = redisObjectTemplate.opsForList();
        ops.leftPush(key, value);
    }
}
