package com.lijuan.servicepassengeruser.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PassengerUser {
    private long id;
    private LocalDateTime gmt_create;
    private LocalDateTime gmt_modified;
    private String passenger_phone;
    private String passenger_name;
    // 注意这用的是byte
    private byte passenger_gender;
    private byte state;
}
