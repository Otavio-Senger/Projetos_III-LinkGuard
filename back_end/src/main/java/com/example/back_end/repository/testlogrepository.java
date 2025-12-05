package com.example.back_end.repository;

import com.example.back_end.model.testlog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface testlogrepository extends JpaRepository<testlog, Long> {
    Optional<testlog> findByTestLogId(String testLogId);
}
