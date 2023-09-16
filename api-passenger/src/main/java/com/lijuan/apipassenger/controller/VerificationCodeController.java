package com.lijuan.apipassenger.controller;

import com.lijuan.apipassenger.service.VerificationCodeService;
import com.lijuan.internalcommon.constant.CommonStatusEnum;
import com.lijuan.internalcommon.dto.ResponseResult;
import com.lijuan.internalcommon.request.VerificationCodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String phone = verificationCodeDTO.getPassengerPhone();
        System.out.println("The passenger's number is: " + phone);

        return verificationCodeService.generateCode(phone);
    }

    @PostMapping("/verification-code-check")
    public ResponseResult verificationCodeCheck(@RequestBody VerificationCodeDTO verificationCodeDTO){

        return verificationCodeService.checkCode(verificationCodeDTO);
    }
}
