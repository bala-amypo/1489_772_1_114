package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Token;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository repo;

    public TokenServiceImpl(TokenRepository repo) {
        this.repo = repo;
    }

    @Override
    public Token create(Token token) {
        token.setIssuedAt(LocalDateTime.now());
        return repo.save(token);
    }

    @Override
    public List<Token> getAll() {
        return repo.findAll();
    }

    @Override
    public Token getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Token update(Long id, Token token) {
        Token existing = repo.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setTokenNumber(token.getTokenNumber());
        existing.setServiceCounterId(token.getServiceCounterId());
        existing.setStatus(token.getStatus());
        existing.setCompletedAt(token.getCompletedAt());

        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
