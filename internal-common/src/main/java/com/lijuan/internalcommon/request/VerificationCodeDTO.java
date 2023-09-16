package com.lijuan.internalcommon.request;

import lombok.Data;

@Data
public class VerificationCodeDTO {
    private String passengerPhone;
    private String numberCode;
}
