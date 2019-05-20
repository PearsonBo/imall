package com.bo.imall.service;


import com.alibaba.dubbo.doc.annotation.InterfaceDesc;
import com.alibaba.dubbo.doc.annotation.MethodDesc;
import com.alibaba.dubbo.doc.annotation.MethodParamDesc;
import com.bo.imall.model.admin.AdminUser;

/**
 * 后台用户登录接口
 *
 * @author bb
 */
@InterfaceDesc("后台用户登录接口")
public interface AdminUserService {

    /**
     * 注册
     *
     * @param adminUser 后台用户
     * @return
     */
    @MethodDesc("获取CarId")
    @MethodParamDesc({
            "adminUser: 后台用户"
    })
    void register(AdminUser adminUser);

    /**
     * 登录
     *
     * @param adminUser
     */
    void login(AdminUser adminUser);

    /**
     * 登出
     *
     * @param adminUser
     */
    void logout(AdminUser adminUser);

    /**
     * 修改密码
     *
     * @param adminUser
     */
    void changePassword(AdminUser adminUser);

}
