package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Token;

public interface TokenService {

    Token generateToken(Long counterId);
    List<Token> getAllTokens();
}
