package com.lambo.ndp.constant;

import com.lambo.common.base.BaseResult;

/**
 * upms系统常量枚举类
 * Created by lambo on 2017/2/18.
 */
public class NdpResult extends BaseResult {

    public NdpResult(NdpResultConstant ndpResultConstant, Object data) {
        super(ndpResultConstant.getCode(), ndpResultConstant.getMessage(), data);
    }

}
