package com.lijuan.service;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeService {

    public String generateCode(String passengerPhone){

        // 产生一个code
        System.out.println("generate a code");
        // 保存到redis
        System.out.println("save the code to redis");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 1);
        jsonObject.put("message", "success");

        return jsonObject.toString();
    }
}
