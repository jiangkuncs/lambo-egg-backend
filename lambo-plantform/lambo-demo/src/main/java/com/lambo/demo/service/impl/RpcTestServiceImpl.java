package com.lambo.demo.service.impl;

import com.lambo.demo.service.api.RpcTestService;
import org.springframework.stereotype.Service;

/**
 * @ClassName RpcTestServiceImpl
 * @Descirption TODO
 * @Author sunzhen
 * @Date 2018/6/22 9:23
 **/
@Service
public class RpcTestServiceImpl implements RpcTestService {

    @Override
    public String sayHello(String name) {
        return "Hi " + name;
    }
}
