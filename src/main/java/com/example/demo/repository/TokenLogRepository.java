package com.example.demo.repository;

import com.example.demo.model.TokenLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenLogRepository extends JpaRepository<TokenLog, Long> {
    // You can add custom queries if needed
}
