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
public class DeviceService{
    private final devicerepository deviceRepository;
    private final testlogrepository testLogRepository;
    private final pingservice pingService;

    public DeviceService(devicerepository deviceRepository, testlogrepository testLogRepository, pingservice pingService) {
        this.deviceRepository = deviceRepository;
        this.testLogRepository = testLogRepository;
        this.pingService = pingService;
    }

    public device createDevice(device newDevice) {
        Optional<device> exists = deviceRepository.findByIpAddress(newDevice.getIpAddress());
        if (exists.isPresent()) {
            throw new IllegalArgumentException("Já existe um dispositivo com este endereço IP.");
        }
        newDevice.setCreated_at(LocalDateTime.now());
        return deviceRepository.save(newDevice);
    }

    public List<device> listDevices(boolean refreshStatus) {
        List<device> devices = deviceRepository.findAll();
        return devices;
        }

        @Transactional
        public testlog runTest(Long deviceId){
            device device = deviceRepository.findByID(deviceId).orElseThrow(() -> new IllegalArgumentException("Dispositivo não encontrado."));

            var res = pingService.pingDevice(device.getIpAddress(), 3000);

            testlog log = new testlog();
            log.setDevice_id(device.getId());
            log.setTimestamp(LocalDateTime.now());
            log.setLatency(res.getLatency());
            log.setStatus(res.isReachable() ? com.example.back_end.enums.status.REACHABLE : com.example.back_end.enums.status.UNREACHABLE);

            return log;
        }
        public List<testLog> getLogs(Long deviceId) {
            return testLogRepository.findByDeviceIdOrderByTimestampDesc(deviceId);
        }
}