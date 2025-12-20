package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Token;

public interface TokenService {

    Token saveToken(Token token);

    List<Token> getAllTokens();

    Token getTokenById(Long id);

    Token updateToken(Long id, Token token);

    void deleteToken(Long id);
}
