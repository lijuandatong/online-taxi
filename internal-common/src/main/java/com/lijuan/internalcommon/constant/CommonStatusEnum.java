package com.lijuan.internalcommon.constant;

import lombok.Getter;

public enum CommonStatusEnum {
    /**
     * 验证码错误提示：1000-1099
     */
    VERIFICATION_CODE_ERROR(1099,"验证码不正确"),
    SUCCESS(1, "success"),
    FAIL(0, "fail");

    @Getter
    private int code;
    @Getter
    private String message;

    CommonStatusEnum(int code, String message){
        this.code = code;
        this.message = message;
    }
}
