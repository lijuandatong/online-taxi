package com.lijuan.serviceverificationcode.controller;

import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public String numberCode(@PathVariable("size") int size){
        System.out.println("The length of number code is: " + size);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 1);
        jsonObject.put("message", "success");
        JSONObject dataObject = new JSONObject();
        // 生成验证码
        double tempCode = (Math.random() + 1) * Math.pow(10, size - 1);
        System.out.println(tempCode);
        int code = (int)tempCode;
        System.out.println(code);

        dataObject.put("numberCode", code);
        jsonObject.put("data", dataObject);
        return jsonObject.toString();

    }
}
