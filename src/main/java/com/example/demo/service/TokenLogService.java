package com.example.demo.service;

import com.example.demo.entity.TokenLog;
import java.util.List;

public interface TokenLogService {
    TokenLog createLog(TokenLog log);
    TokenLog getLog(Long id);
    List<TokenLog> getAllLogs();
    List<TokenLog> getLogsByToken(Long tokenId);
    void deleteLog(Long id);
}
