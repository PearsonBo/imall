package com.bo.imall.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bo.imall.config.SystemConfig;
import com.bo.imall.check.AdminUserLoginChecker;
import com.bo.imall.check.AdminUserRegisterChecker;
import com.bo.imall.dao.AdminUserDao;
import com.bo.imall.model.admin.AdminUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

@Service(version = SystemConfig.SPI_VERSION)
@org.springframework.stereotype.Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserDao adminUserDao;

    @Override
    public void register(AdminUser adminUser) {
        new AdminUserRegisterChecker().check(adminUser);
        adminUserDao.insert(adminUser);
    }

    @Override
    public void login(AdminUser adminUser) {
        new AdminUserLoginChecker().check(adminUser);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                adminUser.getUsername(),
                adminUser.getPassword());
        subject.login(usernamePasswordToken);
    }

    @Override
    public void logout(AdminUser adminUser) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    @Override
    public void changePassword(AdminUser adminUser) {

    }
}
