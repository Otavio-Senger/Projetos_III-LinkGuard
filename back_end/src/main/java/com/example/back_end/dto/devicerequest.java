package com.example.back_end.dto;

import java.time.LocalDateTime;

import com.example.back_end.enums.DeviceType;

public class devicerequest {
    private Long id;
    private String name;
    private String ip_adress;
    private DeviceType type;
    private LocalDateTime created_at;
    
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIp_adress() {
        return ip_adress;
    }

    public DeviceType getType() {
        return type;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }



}
