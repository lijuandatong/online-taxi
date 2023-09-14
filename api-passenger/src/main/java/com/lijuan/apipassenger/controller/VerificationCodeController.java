package com.lijuan.apipassenger.controller;

import com.lijuan.request.VerificationCodeDTO;
import com.lijuan.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @GetMapping("/verification-code")
    public String verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String phone = verificationCodeDTO.getPassengerPhone();
        System.out.println("The passenger's number is: " + phone);

        return verificationCodeService.generateCode(phone);
    }
}
