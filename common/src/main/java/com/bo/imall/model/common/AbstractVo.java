package com.bo.imall.model.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 基础VO提供创建更新人，时间，域，返回值
 *
 * @author admin
 */
@Data
public class AbstractVo implements Serializable {

    private static final long serialVersionUID = 7507237342343039394L;

    private Long id;

    /**
     * 乐观锁
     */
    private Integer lockVersion;

    /**
     * 返回值1
     */
    private String udf1;

    /**
     * 返回值2
     */
    private String udf2;

    /**
     * 返回值3
     */
    private String udf3;

    /**
     * 返回值4
     */
    private String udf4;

    /**
     * 返回值5
     */
    private String udf5;

    /**
     * 返回值6
     */
    private String udf6;

}
