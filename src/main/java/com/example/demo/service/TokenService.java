package com.example.demo.service;

import com.example.demo.entity.Token;
import java.util.List;

public interface TokenService {

    Token createToken(Token token);

    Token updateTokenStatus(Long tokenId, String status);

    List<Token> getTokensByCounterAndStatus(Long counterId, String status);

    Token getTokenByNumber(String tokenNumber);

    List<Token> getAllTokens();

    void deleteToken(Long tokenId);
}
