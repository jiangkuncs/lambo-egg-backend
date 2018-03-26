package com.lambo.ndp.constant;

import com.lambo.common.base.BaseResult;

/**
 * 验证码系统常量枚举类
 * Created by lambo on 2017/2/18.
 */
public class IdentCode extends BaseResult {

    public IdentCode(IdentCodeConstant identCodeConstant, Object data) {
        super(identCodeConstant.getCode(), identCodeConstant.getMessage(), data);
    }

}
