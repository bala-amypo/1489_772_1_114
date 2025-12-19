package com.example.demo.service;

import com.example.demo.entity.TokenLog;
import java.util.List;

public interface TokenLogService {

    TokenLog saveTokenLog(TokenLog tokenLog);

    List<TokenLog> getAllTokenLogs();

    TokenLog getTokenLogById(Long id);

    void deleteTokenLog(Long id);

    List<TokenLog> getLogsByTokenId(Long tokenId);
}
