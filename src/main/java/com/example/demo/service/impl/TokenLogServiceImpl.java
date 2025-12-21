package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.TokenLog;
import com.example.demo.repository.TokenLogRepository;
import com.example.demo.service.TokenLogService;

@Service
public class TokenLogServiceImpl implements TokenLogService {

    private final TokenLogRepository repo;

    public TokenLogServiceImpl(TokenLogRepository repo) {
        this.repo = repo;
    }

    @Override
    public TokenLog create(TokenLog log) {
        log.setCreatedAt(LocalDateTime.now());
        return repo.save(log);
    }

    @Override
    public List<TokenLog> getAll() {
        return repo.findAll();
    }

    @Override
    public TokenLog getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
