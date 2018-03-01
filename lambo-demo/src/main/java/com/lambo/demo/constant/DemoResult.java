package com.lambo.demo.constant;

import com.lambo.common.base.BaseResult;

/**
 * upms系统常量枚举类
 * Created by lambo on 2017/2/18.
 */
public class DemoResult extends BaseResult {

    public DemoResult(DemoResultConstant demoResultConstant, Object data) {
        super(demoResultConstant.getCode(), demoResultConstant.getMessage(), data);
    }

}
