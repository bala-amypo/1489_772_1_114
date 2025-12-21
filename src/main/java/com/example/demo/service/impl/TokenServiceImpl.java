package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.Token;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.ServiceCounterRepository;
import com.example.demo.service.TokenService;
import com.example.demo.entity.ServiceCounter;
import java.util.List;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepo;
    private final ServiceCounterRepository counterRepo;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepo, ServiceCounterRepository counterRepo) {
        this.tokenRepo = tokenRepo;
        this.counterRepo = counterRepo;
    }

    @Override
    public Token createToken(Token token) {
        ServiceCounter counter = counterRepo.findById(token.getServiceCounter().getId())
                .orElseThrow(() -> new RuntimeException("Service Counter not found"));
        token.setServiceCounter(counter);
        return tokenRepo.save(token);
    }

    @Override
    public Token getToken(Long id) {
        return tokenRepo.findById(id).orElseThrow(() -> new RuntimeException("Token not found"));
    }

    @Override
    public List<Token> getAllTokens() {
        return tokenRepo.findAll();
    }

    @Override
    public Token updateToken(Long id, Token token) {
        Token existing = getToken(id);
        existing.setTokenNumber(token.getTokenNumber());
        existing.setStatus(token.getStatus());
        existing.setIssuedAt(token.getIssuedAt());
        existing.setCompletedAt(token.getCompletedAt());
        return tokenRepo.save(existing);
    }

    @Override
    public void deleteToken(Long id) {
        tokenRepo.deleteById(id);
    }
}
