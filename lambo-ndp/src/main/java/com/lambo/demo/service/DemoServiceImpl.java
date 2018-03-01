package com.lambo.demo.service;

import org.springframework.stereotype.Service;

/**
 * 实现DemoService接口
 * Created by lambo on 2017/4/1.
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }

}