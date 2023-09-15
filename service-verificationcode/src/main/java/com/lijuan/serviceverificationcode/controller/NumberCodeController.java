package com.lijuan.serviceverificationcode.controller;

import com.lijuan.internalcommon.dto.ResponseResult;
import com.lijuan.internalcommon.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size){
        System.out.println("The length of number code is: " + size);

        // 生成验证码
        double tempCode = (Math.random() + 1) * Math.pow(10, size - 1);
        System.out.println(tempCode);
        int code = (int)tempCode;
        System.out.println("generate the code: " + code);

        NumberCodeResponse numberCodeResponse = new NumberCodeResponse();
        numberCodeResponse.setNumberCode(code);

        return ResponseResult.success(numberCodeResponse);

    }
}
