package com.example.demo.service;

import com.example.demo.model.TokenLog;
import com.example.demo.repository.TokenLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TokenLogService {

    private final TokenLogRepository tokenLogRepository;

    public TokenLogService(TokenLogRepository tokenLogRepository) {
        this.tokenLogRepository = tokenLogRepository;
    }

    // Create
    public TokenLog createLog(TokenLog tokenLog) {
        return tokenLogRepository.save(tokenLog);
    }

    // Read All
    public List<TokenLog> getAllLogs() {
        return tokenLogRepository.findAll();
    }

    // Read by Id
    public Optional<TokenLog> getLogById(Long id) {
        return tokenLogRepository.findById(id);
    }

    // Update
    public TokenLog updateLog(TokenLog tokenLog) {
        return tokenLogRepository.save(tokenLog);
    }

    // Delete
    public void deleteLog(Long id) {
        tokenLogRepository.deleteById(id);
    }
}
