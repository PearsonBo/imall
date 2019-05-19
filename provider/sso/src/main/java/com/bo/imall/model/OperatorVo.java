package com.bo.imall.model;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OperatorVo {

    private static final long serialVersionUID = -6925622707395780759L;

    /**
     * id
     */
    private Long id;


    /**
     * 登录名
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    private String nameCn;

    /**
     * 用户名英文
     */
    private String nameEn;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话号码
     */
    private String phoneCode;

    /**
     * 网络权限 （内网 vpn 外网）（111）全权限
     */
    private String netAuthority;

    /**
     * ldap用户
     */
    private Boolean ldap;

    /**
     * 盐
     */
    private String salt;

    /**
     * 登录时间
     */
    private Date signTime;

    /**
     * 上次登录时间
     */
    private Date lastLoginTime;

    /**
     * 上次试图登录时间
     */
    private Date lastAttemptedLoginTime;

    /**
     * 登录错误次数
     */
    private Integer passwordErrorTimes;

    /**
     * 用户关联角色集合
     */
    private List<Long> roleIdList;

    /**
     * 记住我
     */
    private Boolean rememberMe;

    /**
     * 验证码
     */
    private String kaptcha;
}
