package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.TokenLog;

public interface TokenLogService {

    TokenLog create(TokenLog log);

    List<TokenLog> getAll();

    TokenLog getById(Long id);

    void delete(Long id);
}
