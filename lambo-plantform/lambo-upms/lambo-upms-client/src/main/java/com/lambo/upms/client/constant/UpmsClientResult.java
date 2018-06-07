package com.lambo.upms.client.constant;

import com.lambo.common.base.BaseResult;

/**
 * upms系统常量枚举类
 * Created by lambo on 2017/2/18.
 */
public class UpmsClientResult extends BaseResult {

    public UpmsClientResult(UpmsClientResultConstant upmsClientResultConstant, Object data) {
        super(upmsClientResultConstant.getCode(), upmsClientResultConstant.getMessage(), data);
    }

}
