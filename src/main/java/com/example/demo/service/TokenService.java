package com.example.demo.service;

import com.example.demo.entity.Token;
import java.util.List;

public interface TokenService {
    Token saveToken(Token token);
    List<Token> getAllTokens();
    Token getTokenById(Long id);  // <-- needed for GET by ID
    Token updateToken(Long id, Token token); // optional for PUT
    void deleteToken(Long id);    // optional for DELETE
}
