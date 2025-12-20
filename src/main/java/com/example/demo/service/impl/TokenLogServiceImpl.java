package com.example.demo.service.impl;

import com.example.demo.entity.Token;
import com.example.demo.entity.TokenLog;
import com.example.demo.repository.TokenLogRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.TokenLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenLogServiceImpl implements TokenLogService {

    @Autowired
    private TokenLogRepository tokenLogRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public TokenLog saveLog(TokenLog tokenLog) {

        Token token = tokenRepository.findById(tokenLog.getTokenId())
                .orElseThrow(() ->
                        new RuntimeException("Token not found with id: " + tokenLog.getTokenId())
                );

        tokenLog.setToken(token);
        return tokenLogRepository.save(tokenLog);
    }

    @Override
    public List<TokenLog> getLogsByTokenId(Long tokenId) {
        return tokenLogRepository.findByToken_IdOrderByLoggedAtAsc(tokenId);
    }
}
