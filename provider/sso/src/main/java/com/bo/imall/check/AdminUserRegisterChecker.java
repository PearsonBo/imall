package com.bo.imall.check;


import com.bo.imall.exception.ExceptionType;
import com.bo.imall.model.admin.AdminUser;
import com.bo.imall.util.CheckEmptyUtil;

/**
 * @Author: Hu Jianbo
 * @Date: 2019/5/19 0019 下午 16:57
 */
public class AdminUserRegisterChecker implements AdminUserChecker {

    @Override
    public void check(AdminUser adminUser) {
        CheckEmptyUtil.checkString(adminUser.getUsername(), "注册用户名不能为空", ExceptionType.INTERFACE_PARAM);
        CheckEmptyUtil.checkString(adminUser.getPassword(), "注册密码不能为空", ExceptionType.INTERFACE_PARAM);
    }
}
