package com.example.demo.service;

import com.example.demo.entity.Token;
import java.util.List;

public interface TokenService {
    Token createToken(Token token);
    Token getToken(Long id);
    List<Token> getAllTokens();
    Token updateToken(Long id, Token token);
    void deleteToken(Long id);
}
