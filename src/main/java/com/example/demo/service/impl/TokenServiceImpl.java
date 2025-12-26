package com.example.demo.service.impl;

import com.example.demo.entity.ServiceCounter;
import com.example.demo.entity.Token;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public Token issueToken(ServiceCounter counter, String tokenNumber) {
        Token token = new Token();
        token.setServiceCounter(counter);
        token.setTokenNumber(tokenNumber); // make sure Token.java has this field
        token.setStatus("Issued");
        token.setIssuedAt(LocalDateTime.now());
        return tokenRepository.save(token);
    }

    @Override
    public void completeToken(Token token) {
        token.setStatus("Completed");
        token.setCompletedAt(LocalDateTime.now());
        tokenRepository.save(token);
    }
}
