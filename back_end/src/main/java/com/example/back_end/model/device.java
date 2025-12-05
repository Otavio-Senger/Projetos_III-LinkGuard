package com.example.back_end.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.example.back_end.enums.DeviceType;


@Entity
@Table(name = "devices")
public class device {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String ip_adress;

    @Column(nullable = false)
    private DeviceType type;

    @Column(nullable = false)
    private LocalDateTime created_at;


    public device() {
    }

    public Long getId() {
        return id;
    }       

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIp_adress() {
        return ip_adress;
    }
    public void setIp_adress(String ip_adress) {
        this.ip_adress = ip_adress;
    }
    public DeviceType getType() {
        return type;
    }
    public void setType(DeviceType type) {
        this.type = type;
    }
    public LocalDateTime getCreated_at() {
        return created_at;
    }
    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }






}
