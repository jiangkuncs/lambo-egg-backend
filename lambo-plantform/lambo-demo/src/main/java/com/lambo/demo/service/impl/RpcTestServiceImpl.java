package com.lambo.demo.service.impl;

import com.lambo.demo.service.api.RpcTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @ClassName RpcTestServiceImpl
 * @Descirption TODO
 * @Author sunzhen
 * @Date 2018/6/22 9:23
 **/
@Service
public class RpcTestServiceImpl implements RpcTestService {

    private static Logger logger = LoggerFactory.getLogger(RpcTestServiceImpl.class);

    @Override
    @Cacheable(value = "Cache60Seconds", key = "'demo-test-sayHello-'+ #name")
    public String sayHello(String name) {
        logger.info("name==============" + name);
        return "Hi " + name;
    }

    @Override
    @CacheEvict(value = "Cache60Seconds", key = "'demo-test-sayHello-'+ #name")
    public void updateSometing(String name) {
        logger.info("update==============" + name);
    }
}
