package com.lambo.ndp.constant;

/**
 * 验证码系统接口结果常量枚举类
 * Created by lambo on 2017/2/18.
 */
public enum IdentCodeConstant {

    FAILED(0, "failed"),
    SUCCESS(1, "success"),
    OPEN_IDENT_CODE(20001, "get ident code"),
    IDENT_CODE_CORRECT(20002,"ident code correct"),
    IDENT_CODE_ERROR(20003,"ident code error");

    public int code;

    public String message;

    IdentCodeConstant(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
