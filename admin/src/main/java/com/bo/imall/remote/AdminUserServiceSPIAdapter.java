package com.bo.imall.remote;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bo.imall.config.SystemConfig;
import com.bo.imall.model.admin.AdminUser;
import com.bo.imall.service.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminUserServiceSPIAdapter implements AdminUserServiceSPI {

    @Reference(version = SystemConfig.SPI_VERSION)
    private AdminUserService adminUserService;


    @Override
    public void register(AdminUser adminUser) {
        try {
            adminUserService.register(adminUser);
        } catch (Exception e) {
            log.error("com.bo.imall.service.AdminUserService.register  调用异常 param adminUser:{}", adminUser, e);
        }
    }

    @Override
    public void login(AdminUser adminUser) {
        try {
            adminUserService.login(adminUser);
        } catch (Exception e) {
            log.error("com.bo.imall.service.AdminUserService.login  调用异常 param adminUser:{}", adminUser, e);
        }
    }

    @Override
    public void logout(AdminUser adminUser) {
        try {
            adminUserService.logout(adminUser);
        } catch (Exception e) {
            log.error("com.bo.imall.service.AdminUserService.logout  调用异常 param adminUser:{}", adminUser, e);
        }
    }

    @Override
    public void changePassword(AdminUser adminUser) {
        try {
            adminUserService.changePassword(adminUser);
        } catch (Exception e) {
            log.error("com.bo.imall.service.AdminUserService.changePassword  调用异常 param adminUser:{}", adminUser, e);
        }
    }
}
