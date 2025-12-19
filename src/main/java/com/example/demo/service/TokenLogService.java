package com.example.demo.service;

import com.example.demo.entity.TokenLog;
import java.util.List;

public interface TokenLogService {

    TokenLog createTokenLog(TokenLog tokenLog);

    List<TokenLog> getAllTokenLogs();

    TokenLog getTokenLogById(Long id);

    List<TokenLog> getTokenLogsByTokenId(Long tokenId);
}
