package com.bo.imall.service;

import com.bo.imall.RedisApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

/**
 * @Author: PearsonBo
 * @Date: 2019-05-22 16:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
public class RedisServiceImplTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void test() {
        redisService.set("k1", "v1");
        Serializable k1 = redisService.get("k1");
        System.out.println(k1);
    }
}