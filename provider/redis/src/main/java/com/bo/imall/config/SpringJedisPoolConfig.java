package com.bo.imall.config;

import lombok.Data;


/**
 * @author HuJianbo
 */
@Data
public class SpringJedisPoolConfig {

    private Integer maxActive;

    private Integer maxWait;

    private Integer maxIdle;

    private Integer minIdle;
}
