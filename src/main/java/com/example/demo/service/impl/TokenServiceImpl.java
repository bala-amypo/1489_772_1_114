
package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.example.demo.entity.Token;
import com.example.demo.repository.TokenRepository;
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
}
