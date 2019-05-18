package com.bo.imall.service;

import com.bo.imall.model.admin.AdminUser;

/**
 * 后台用户登录接口
 *
 * @author bb
 */
public interface AdminUserService {

    /**
     * 注册
     *
     * @param adminUser
     */
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
