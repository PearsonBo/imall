package com.bo.imall.remote;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bo.imall.config.SystemConfig;
import com.bo.imall.service.SsoService;
import org.springframework.stereotype.Service;

@Service
public class SsoServiceAdapter {

    @Reference(version = SystemConfig.SPI_VERSION)
    private SsoService ssoService;

    public void test() {
        ssoService.test();
    }

}
