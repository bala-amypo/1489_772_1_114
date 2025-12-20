package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        ServiceCounter counter = counterRepo.findById(token.getServiceCounter().getId())
                .orElseThrow(() -> new RuntimeException("Service Counter not found"));
        token.setServiceCounter(counter);
        return repo.save(token);
    }

    @Override
    public List<Token> getAllTokens() {
        return repo.findAll();
    }

    @Override
    public void deleteToken(Long id) {
        repo.deleteById(id);
    }
}
