package com.example.back_end.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.example.back_end.enums.status;

@Entity
@Table(name = "test")
public class testlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long device_id;

    @Column(nullable = false)
    private status status;

    @Column(nullable = false)
    private String latency;

    @Column(nullable = false)
    private LocalDateTime timestamp;
    


    public testlog() {
    }

    

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getDevice_id() {
        return device_id;
    }
    public void setDevice_id(long device_id) {
        this.device_id = device_id;
    }
    public status getStatus() {
        return status;
    }
    public void setStatus(status status) {
        this.status = status;
    }
    public String getLatency() {
        return latency;
    }
    public void setLatency(String latency) {
        this.latency = latency;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}