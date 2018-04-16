package com.lambo.oss.client.constant;

import com.lambo.common.base.BaseResult;

/**
 * upms系统常量枚举类
 * Created by lambo on 2017/2/18.
 */
public class OssResult extends BaseResult {

    public OssResult(OssResultConstant ossResultConstant, Object data) {
        super(ossResultConstant.getCode(), ossResultConstant.getMessage(), data);
    }

}
