package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.entity.Token;
import com.example.demo.repository.TokenRepository;
import java.time.LocalDateTime;

@Service
public class TokenService {

    private final TokenRepository repo;

    public TokenService(TokenRepository repo) {
        this.repo = repo;
    }

    public Token save(Token token) {
        token.setIssuedAt(LocalDateTime.now());
        return repo.save(token);
    }
}
