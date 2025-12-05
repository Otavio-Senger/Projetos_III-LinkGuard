package com.example.back_end.repository;

import com.example.back_end.model.device;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface devicerepository extends JpaRepository<device, Long> {
    Optional<device> findByIpAdress(String deviceIP);
}
