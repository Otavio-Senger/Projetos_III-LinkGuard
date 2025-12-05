package com.example.back_end.service;

import com.example.back_end.model.device;
import com.example.back_end.model.testlog;
import com.example.back_end.repository.devicerepository;
import com.example.back_end.repository.testlogrepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;


@Service
public class deviceservice {
    private final devicerepository devicerepository;
    private final testlogrepository testlogrepository;
    private final pingservice pingservice;


    public deviceservice(devicerepository devicerepository, testlogrepository testlogrepository, pingservice pingservice) {
        this.devicerepository = devicerepository;
        this.testlogrepository = testlogrepository;
        this.pingservice = pingservice;
    }

    public device createDevice(device device) {
        Optional<device> existingDevice = devicerepository.findByIpAdress(device.getIp_adress());
        if (existingDevice.isPresent()) {
            throw new IllegalArgumentException("Device with IP address " + device.getIp_adress() + " already exists.");
        }
        device.setCreated_at(LocalDateTime.now());
        return devicerepository.save(device);
    }

    
public List<device> listDevices(boolean refreshStatus){
        List<device> devices = devicerepository.findAll();
        if (refreshStatus) {
            for (device d : devices){
                try{
                    var res = pingservice.pingDevice(d.getIp_adress(), 3000);
                    d.setCreated_at(LocalDateTime.now());
                    devicerepository.save(d);
                } catch (Exception ex){
                    d.setCreated_at(LocalDateTime.now());
                    devicerepository.save(d);
            }
        }
        devices = devicerepository.findAll();
    }
    return devices;
}

}
