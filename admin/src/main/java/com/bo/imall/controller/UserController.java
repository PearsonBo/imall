package com.bo.imall.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bo.imall.config.SystemConfig;
import com.bo.imall.model.admin.AdminUser;
import com.bo.imall.model.common.PackVo;
import com.bo.imall.service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: bo
 * @Date: 2019/5/19 0019 下午 15:29
 */
@RestController
@Api(description = "后台管理员相关接口")
@Slf4j
public class UserController {

    @Reference(version = SystemConfig.SPI_VERSION)
    private AdminUserService adminUserService;

    @PostMapping
    @ApiOperation(value = "注册")
    public PackVo register(AdminUser adminUser) {
        log.debug("start register..");
        adminUserService.register(adminUser);
        return new PackVo();
    }

    @PostMapping
    @ApiOperation(value = "登录")
    public PackVo login(AdminUser adminUser) {
        log.debug("start login..");
        adminUserService.login(adminUser);
        return new PackVo();
    }

    @PostMapping
    @ApiOperation(value = "登出")
    public PackVo logout(AdminUser adminUser) {
        log.debug("start logout..");
        adminUserService.logout(adminUser);
        return new PackVo();
    }

    @PostMapping
    @ApiOperation(value = "修改密码")
    public PackVo changePassword(AdminUser adminUser) {
        log.debug("start changePassword..");
        adminUserService.changePassword(adminUser);
        return new PackVo();
    }
}
