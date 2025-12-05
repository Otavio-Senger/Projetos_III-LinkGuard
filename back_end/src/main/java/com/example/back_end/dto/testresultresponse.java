package com.example.back_end.dto;

import com.example.back_end.enums.status;
import java.time.LocalDateTime;

public class testresultresponse {
    private Long id;
    private Long device_id;
    private status status;
    private String latency;
    private LocalDateTime timestamp;

    public testresultresponse() {
    }

    public testresultresponse(Long id, Long device_id, status status, String latency, LocalDateTime timestamp) {
        this.id = id;
        this.device_id = device_id;
        this.status = status;
        this.latency = latency;
        this.timestamp = timestamp;
    }


    public Long getId() {
        return id;
    }
    public Long getDevice_id() {
        return device_id;
    }
    public status getStatus() {
        return status;
    }
    public String getLatency() {
        return latency;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    

}
