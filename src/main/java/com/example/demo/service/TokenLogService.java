package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.entity.TokenLog;
import com.example.demo.repository.TokenLogRepository;
import java.time.LocalDateTime;

@Service
public class TokenLogService {

    private final TokenLogRepository repo;

    public TokenLogService(TokenLogRepository repo) {
        this.repo = repo;
    }

    public TokenLog save(TokenLog log) {
        log.setActionTime(LocalDateTime.now());
        return repo.save(log);
    }
}
