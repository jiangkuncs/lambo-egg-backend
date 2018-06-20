package com.lambo.auth.client.constant;

/**
 * upms系统接口结果常量枚举类
 * Created by lambo on 2017/2/18.
 */
public enum AuthClientResultConstant {

    FAILED(0, "failed"),
    SUCCESS(1, "success"),
    ACCESS_DENIED(10106,"Access_Denied"),
    INVALID_LENGTH(10001, "Invalid length"),
    EMPTY_USERNAME(10101, "Username cannot be empty"),
    EMPTY_PASSWORD(10102, "Password cannot be empty"),
    INVALID_USERNAME(10103, "Account does not exist"),
    INVALID_PASSWORD(10104, "Password error"),
    INVALID_ACCOUNT(10105, "Invalid account");

    public int code;

    public String message;

    AuthClientResultConstant(int code, String message) {
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
