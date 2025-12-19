package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Token;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public Token saveToken(Token token) {
        return tokenRepository.save(token);
    }

    @Override
    public List<Token> getAllTokens() {
        return tokenRepository.findAll();
    }

    @Override
    public Token getTokenById(Long id) {
        return tokenRepository.findById(id).orElse(null);
    }

    @Override
    public Token updateToken(Long id, Token token) {
        Token existing = tokenRepository.findById(id).orElse(null);

        if (existing != null) {
            existing.setTokenNumber(token.getTokenNumber());
            existing.setServiceCounter(token.getServiceCounter());
            existing.setStatus(token.getStatus());
            existing.setIssuedAt(token.getIssuedAt());
            existing.setCompletedAt(token.getCompletedAt());
            return tokenRepository.save(existing);
        }
        return null;
    }

    @Override
    public void deleteToken(Long id) {
        tokenRepository.deleteById(id);
    }
}
