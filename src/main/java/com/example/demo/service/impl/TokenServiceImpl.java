package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private TokenLogRepository tokenLogRepository;

    @Override
    public Token issueToken(Token token) {
        long count = tokenRepository.count() + 1;
        token.setTokenNumber("T-" + count);
        token.setStatus(TokenStatus.WAITING);
        return tokenRepository.save(token);
    }

    @Override
    public Token updateStatus(Long tokenId, TokenStatus newStatus) {
        Token token = tokenRepository.findById(tokenId).orElse(null);
        if (token == null) return null;

        token.setStatus(newStatus);

        if (newStatus == TokenStatus.SERVING) {
            token.setServedAt(LocalDateTime.now());
        }

        if (newStatus == TokenStatus.COMPLETED) {
            token.setCompletedAt(LocalDateTime.now());
        }

        Token saved = tokenRepository.save(token);

        TokenLog log = new TokenLog();
        log.setToken(saved);
        log.setStatus(newStatus);
        log.setTimestamp(LocalDateTime.now());
        tokenLogRepository.save(log);

        return saved;
    }

    @Override
    public Token findByTokenNumber(String tokenNumber) {
        return tokenRepository.findByTokenNumber(tokenNumber).orElse(null);
    }

    @Override
    public List<Token> findAll() {
        return tokenRepository.findAll();
    }
}
