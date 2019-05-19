package com.bo.imall.model.admin;

import com.bo.imall.model.common.AbstractVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class AdminUserVo extends AbstractVo {

    @ApiModelProperty(name = "名称")
    private String username;

    @ApiModelProperty(name = "密码")
    private String password;

    @ApiModelProperty(name = "登录次数")
    private Integer loginNum;

    @ApiModelProperty(name = "最近一次登录时间")
    private Date lastLoginTime;

}
