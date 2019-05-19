package com.bo.imall.model.common;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 抽象基础PO 提供创建更新及域信息
 *
 * @author admin
 */
@Data
public abstract class AbstractBo implements Serializable {

    private static final long serialVersionUID = 1993072186482038252L;

    /**
     * 编号
     */
    private Long id;

    /**
     * 乐观锁
     */
    private Integer lockVersion;

}
