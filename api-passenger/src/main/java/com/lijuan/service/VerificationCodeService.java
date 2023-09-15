package com.lijuan.service;

import com.lijuan.remote.ServiceVerificationCodeClient;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeService {
    @Autowired
    private ServiceVerificationCodeClient serviceVerificationCodeClient;

    public String generateCode(String passengerPhone){

        // 产生一个code
        int code = serviceVerificationCodeClient.getNumberCode(6).getData().getNumberCode();
        System.out.println("receive the remote code: " + code);
        // 保存到redis
        System.out.println("save the code to redis");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 1);
        jsonObject.put("message", "success");

        return jsonObject.toString();
    }
}
