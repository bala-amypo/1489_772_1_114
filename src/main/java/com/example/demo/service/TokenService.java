package com.example.demo.service;

import com.example.demo.entity.Token;
import com.example.demo.entity.TokenStatus;
import java.util.List;

public interface TokenService {
    Token issueToken(Token token);
    Token updateStatus(Long tokenId, TokenStatus status);
    Token findByTokenNumber(String tokenNumber);
    List<Token> findAll();
}
