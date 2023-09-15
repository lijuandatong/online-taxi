package com.lijuan.internalcommon.constant;

import lombok.Getter;

public enum CommonStatusEnum {
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
