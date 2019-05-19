package com.bo.imall.model.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 基础搜索类
 *
 * @author admin
 */
@Data
public class AbstractSo implements Serializable {

    private static final long serialVersionUID = 4009650342175211289L;

    /**
     * 默认的页面数
     */
    public static final Integer DEFAULT_PAGE_SIZE = 50;

    /**
     * 创建人id
     */
    private Long creatorId;

    /**
     * 当前页面数
     */
    private Integer pageNumber = 1;

    /**
     * 页面数
     */
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    /**
     * objectId list
     */
    private List<Long> idList;

    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

}
