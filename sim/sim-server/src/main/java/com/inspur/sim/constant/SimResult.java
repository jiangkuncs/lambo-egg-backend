package com.inspur.sim.constant;

import com.lambo.common.base.BaseResult;

/**
 * upms系统常量枚举类
 * Created by lambo on 2017/2/18.
 */
public class SimResult extends BaseResult {

    public SimResult(SimResultConstant simResultConstant, Object data) {
        super(simResultConstant.getCode(), simResultConstant.getMessage(), data);
    }

}
