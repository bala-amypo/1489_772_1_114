package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Token;
import com.example.demo.repository.TokenRepository;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepo;

    public TokenServiceImpl(TokenRepository tokenRepo) {
        this.tokenRepo = tokenRepo;
    }

    @Override
    public Token createToken(Token token) {
        return tokenRepo.save(token);
    }

    @Override
    public List<Token> getAllTokens() {
        return tokenRepo.findAll();
    }
}
