package com.bo.imall.config;import lombok.Data;import org.springframework.boot.context.properties.ConfigurationProperties;import org.springframework.context.annotation.Configuration;/** * @Author: PearsonBo * @Date: 2019-05-22 16:30 */@Configuration@ConfigurationProperties(prefix = "spring.redis")@Datapublic class RedisConfig {    private String host;    private int port;    private String password;    private int timeout;    private int database;    private SpringJedisPoolConfig pool;    @Override    public String toString() {        return "Redis [localhost=" + host + ", port=" + port + ", timeout=" + timeout + "]";    }}