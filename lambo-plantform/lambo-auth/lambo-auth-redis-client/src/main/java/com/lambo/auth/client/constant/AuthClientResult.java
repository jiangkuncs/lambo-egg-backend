package com.lambo.auth.client.constant;

import com.lambo.common.base.BaseResult;

/**
 * authClient系统常量枚举类
 * Created by lambo on 2017/2/18.
 */
public class AuthClientResult extends BaseResult {

    public AuthClientResult(AuthClientResultConstant authClientResultConstant, Object data) {
        super(authClientResultConstant.getCode(), authClientResultConstant.getMessage(), data);
    }

}
