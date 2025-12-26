package com.example.demo.service;

import com.example.demo.entity.TokenLog;

import java.util.List;

public interface TokenLogService {

    TokenLog create(TokenLog log);

    List<TokenLog> getAll();

    List<TokenLog> getByTokenId(Long tokenId);
}
