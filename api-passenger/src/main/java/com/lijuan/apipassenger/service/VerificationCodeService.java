package com.lijuan.apipassenger.service;

import com.lijuan.apipassenger.remote.ServicePassengerUserClient;
import com.lijuan.apipassenger.remote.ServiceVerificationCodeClient;
import com.lijuan.internalcommon.constant.CommonStatusEnum;
import com.lijuan.internalcommon.constant.IdentityConstants;
import com.lijuan.internalcommon.dto.ResponseResult;
import com.lijuan.internalcommon.request.VerificationCodeDTO;
import com.lijuan.internalcommon.response.TokenResponse;
import com.lijuan.internalcommon.util.JwtUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerificationCodeService {
    @Autowired
    private ServiceVerificationCodeClient serviceVerificationCodeClient;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    public ResponseResult generateCode(String passengerPhone){
        // 调用远程服务获得一个code
        int code = serviceVerificationCodeClient.getNumberCode(6).getData().getNumberCode();
        System.out.println("receive the remote code: " + code);

        // 保存到redis
        System.out.println("save the code to redis");
        String key = verificationCodePrefix + passengerPhone;
        // 2分钟超时时间
        stringRedisTemplate.opsForValue().set(key, code + "", 2, TimeUnit.MINUTES);
        return ResponseResult.success();
    }

    public ResponseResult checkCode(VerificationCodeDTO verificationCodeDTO){
        // 根据手机号码从redis中取出验证码并校验
        String key = generateKeyByPhone(verificationCodeDTO.getPassengerPhone());
        String code = stringRedisTemplate.opsForValue().get(key);
        if(StringUtils.isBlank(code)){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),
                    CommonStatusEnum.VERIFICATION_CODE_ERROR.getMessage());
        }

        String numberCode = verificationCodeDTO.getNumberCode();
        if(!code.trim().equals(numberCode.trim())){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),
                    CommonStatusEnum.VERIFICATION_CODE_ERROR.getMessage());
        }

        // 验证码正确，登录或者注册
        servicePassengerUserClient.loginOrRegister(verificationCodeDTO);

        // 颁发令牌
        String token = JwtUtils.generatorToken(verificationCodeDTO.getPassengerPhone(),
                IdentityConstants.PASSENGER_IDENTITY, "1");
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(token);

        return ResponseResult.success(tokenResponse);
    }

    private String verificationCodePrefix = "passenger-verification-code-";
    private String generateKeyByPhone(String passengerPhone){
        return verificationCodePrefix + passengerPhone;
    }
}
