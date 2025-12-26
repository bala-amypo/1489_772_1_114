package com.example.demo.service.impl;

import com.example.demo.entity.Token;
import com.example.demo.entity.TokenLog;
import com.example.demo.repository.TokenLogRepository;
import com.example.demo.service.TokenLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service   // 🔴 REQUIRED
public class TokenLogServiceImpl implements TokenLogService {

    private final TokenLogRepository repo;

    public TokenLogServiceImpl(TokenLogRepository repo) {
        this.repo = repo;
    }

    @Override
    public TokenLog create(TokenLog log) {
        log.setLoggedAt(LocalDateTime.now());
        return repo.save(log);
    }

    @Override
    public List<TokenLog> getAll() {
        return repo.findAll();
    }

    @Override
    public List<TokenLog> getByTokenId(Long tokenId) {
        return repo.findByTokenId(tokenId);
    }
}
