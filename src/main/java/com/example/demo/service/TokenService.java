package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Token;

public interface TokenService {

    Token create(Token token);

    List<Token> getAll();

    Token getById(Long id);

    Token update(Long id, Token token);

    void delete(Long id);
}
