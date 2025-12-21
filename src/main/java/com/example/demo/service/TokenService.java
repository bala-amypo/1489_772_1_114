package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Token;
import com.example.demo.repository.TokenRepository;

import java.time.LocalDateTime;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public Token createToken(Token token) {
        token.setIssuedAt(LocalDateTime.now());
        return tokenRepository.save(token);
    }

    public Token getToken(Long id) {
        return tokenRepository.findById(id).orElse(null);
    }
}
