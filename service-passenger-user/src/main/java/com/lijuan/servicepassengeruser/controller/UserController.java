package com.lijuan.servicepassengeruser.controller;

import com.lijuan.internalcommon.dto.ResponseResult;
import com.lijuan.internalcommon.request.VerificationCodeDTO;
import com.lijuan.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String phone = verificationCodeDTO.getPassengerPhone();
        return userService.loginOrRegister(phone);
    }
}
