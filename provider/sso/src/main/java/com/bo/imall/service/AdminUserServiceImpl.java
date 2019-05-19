package com.bo.imall.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bo.imall.config.SystemConfig;
import com.bo.imall.check.AdminUserLoginChecker;
import com.bo.imall.check.AdminUserRegisterChecker;
import com.bo.imall.dao.AdminUserDao;
import com.bo.imall.model.admin.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = SystemConfig.SPI_VERSION)
@org.springframework.stereotype.Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserDao adminUserDao;

    @Override
    public void register(AdminUser adminUser) {
        new AdminUserRegisterChecker().check(adminUser);
    }

    @Override
    public void login(AdminUser adminUser) {
        new AdminUserLoginChecker().check(adminUser);

    }

    @Override
    public void logout(AdminUser adminUser) {

    }

    @Override
    public void changePassword(AdminUser adminUser) {

    }
}
