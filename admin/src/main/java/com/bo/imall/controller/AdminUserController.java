package com.bo.imall.controller;

import com.bo.imall.model.admin.AdminUser;
import com.bo.imall.model.common.PackVo;
import com.bo.imall.remote.AdminUserServiceSPIAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: PearsonBo
 * @Date: 2019/5/19 0019 下午 15:29
 */
@RestController(value = AdminUserController.VIEW_PREFIX)
@Api(value = "后台管理员相关接口")
@Slf4j
public class AdminUserController {

    public static final String VIEW_PREFIX = "/rest/admin";
    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";
    public static final String LOGOUT = "/logout";
    public static final String CHANGE_PASSWORD = "/changePassword";

    @Autowired
    private AdminUserServiceSPIAdapter adminUserServiceSPIAdapter;

    @PostMapping(value = REGISTER)
    @ApiOperation(value = "注册")
    public PackVo register(AdminUser adminUser) {
        log.debug("start register..");
        adminUserServiceSPIAdapter.register(adminUser);
        return new PackVo();
    }

    @PostMapping(value = LOGIN)
    @ApiOperation(value = "登录")
    public PackVo login(AdminUser adminUser) {
        log.debug("start login..");
        adminUserServiceSPIAdapter.login(adminUser);
        return new PackVo();
    }

    @PostMapping(value = LOGOUT)
    @ApiOperation(value = "登出")
    public PackVo logout(AdminUser adminUser) {
        log.debug("start logout..");
        adminUserServiceSPIAdapter.logout(adminUser);
        return new PackVo();
    }

    @PostMapping(value = CHANGE_PASSWORD)
    @ApiOperation(value = "修改密码")
    public PackVo changePassword(AdminUser adminUser) {
        log.debug("start changePassword..");
        adminUserServiceSPIAdapter.changePassword(adminUser);
        return new PackVo();
    }
}
