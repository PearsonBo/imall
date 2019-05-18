package com.bo.imall.model.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 后台管理员
 *
 * @author bb
 */
@Data
@ApiModel(description = "后台管理员ø")
public class AdminUser implements Serializable {
    private static final long serialVersionUID = 7463450609483441094L;

    @ApiModelProperty(name = "名称")
    private String username;

    @ApiModelProperty(name = "密码")
    private String password;

    @ApiModelProperty(name = "登录次数")
    private Integer loginNum;

    @ApiModelProperty(name = "最近一次登录时间")
    private Date lastLoginTime;
}
