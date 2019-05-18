package com.bo.imall.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;

/**
 * @author bb
 */
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class SsoServiceImpl implements SsoService {

    @Override
    public void test() {
        System.out.println(String.format("provider: %s, consumer: %s",
                RpcContext.getContext().getLocalAddress(),
                RpcContext.getContext().getRemoteAddress())
        );
    }
}
