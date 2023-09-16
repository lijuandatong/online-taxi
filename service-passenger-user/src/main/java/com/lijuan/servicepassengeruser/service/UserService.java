package com.lijuan.servicepassengeruser.service;

import com.lijuan.internalcommon.dto.ResponseResult;
import com.lijuan.servicepassengeruser.dto.PassengerUser;
import com.lijuan.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private PassengerUserMapper passengerUserMapper;

    public ResponseResult loginOrRegister(String phone){
        System.out.println("手机号为：" +phone);
        // 查询手机号对应的用户
        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", phone);
        List<PassengerUser> list = passengerUserMapper.selectByMap(map);
        // 用户是否存在
        if(list == null || list.size() == 0){
            // 不存在则插入一条记录
            System.out.println("该用户不存在");
            PassengerUser user = new PassengerUser();
            user.setPassenger_phone(phone);
            user.setPassenger_name("张三");
            user.setPassenger_gender((byte)1);
            user.setState((byte)0);
            LocalDateTime now = LocalDateTime.now();
            user.setGmt_create(now);
            user.setGmt_modified(now);
            passengerUserMapper.insert(user);
        }
        return ResponseResult.success();
    }
}
