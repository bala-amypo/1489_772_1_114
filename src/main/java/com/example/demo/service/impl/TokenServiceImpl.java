package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ServiceCounter;
import com.example.demo.entity.Token;
import com.example.demo.repository.ServiceCounterRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private ServiceCounterRepository serviceCounterRepository;

    @Override
    public Token saveToken(Token token) {

        ServiceCounter counter = serviceCounterRepository.findById(token.getServiceCounterId())
                .orElseThrow(() ->
                        new RuntimeException("ServiceCounter not found with id: " + token.getServiceCounterId())
                );

        token.setServiceCounter(counter);

        return tokenRepository.save(token);
    }

    @Override
    public List<Token> getAllTokens() {
        return tokenRepository.findAll();
    }

    @Override
    public Token getTokenById(Long id) {
        return tokenRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Token not found with id: " + id)
                );
    }

    @Override
    public Token updateToken(Long id, Token token) {

        Token existing = getTokenById(id);

        existing.setStatus(token.getStatus());
        existing.setCompletedAt(token.getCompletedAt());

        return tokenRepository.save(existing);
    }

    @Override
    public void deleteToken(Long id) {
        tokenRepository.delete(getTokenById(id));
    }
}
