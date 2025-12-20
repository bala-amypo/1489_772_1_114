package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.example.demo.entity.Token;
import com.example.demo.entity.ServiceCounter;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.ServiceCounterRepository;
import com.example.demo.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository repo;

    @Autowired
    private ServiceCounterRepository counterRepo;

    @Override
    public Token saveToken(Token token) {
        ServiceCounter counter = counterRepo.findById(token.getServiceCounterId())
                .orElseThrow(() -> new RuntimeException("Service Counter not found"));
        token.setServiceCounter(counter);
        return repo.save(token);
    }

    @Override
    public List<Token> getAllTokens() {
        return repo.findAll();
    }

    @Override
    public Token updateToken(Long tokenId, Token updatedToken) {
        Token existing = repo.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));
        existing.setTokenNumber(updatedToken.getTokenNumber());
        // if you want to update service counter:
        if (updatedToken.getServiceCounterId() != null) {
            ServiceCounter counter = counterRepo.findById(updatedToken.getServiceCounterId())
                    .orElseThrow(() -> new RuntimeException("Service Counter not found"));
            existing.setServiceCounter(counter);
        }
        return repo.save(existing);
    }

    @Override
    public void deleteToken(Long tokenId) {
        repo.deleteById(tokenId);
    }
}
