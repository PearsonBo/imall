package com.bo.imall.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bo.imall.model.admin.AdminUser;

/**
 * @author bb
 */
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class AdminUserServiceImpl implements AdminUserService {

    @Override
    public void register(AdminUser adminUser) {

    }

    @Override
    public void login(AdminUser adminUser) {

    }

    @Override
    public void logout(AdminUser adminUser) {

    }

    @Override
    public void changePassword(AdminUser adminUser) {

    }
}
