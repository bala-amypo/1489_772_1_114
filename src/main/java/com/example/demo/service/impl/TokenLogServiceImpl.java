package com.example.demo.service.impl;

import com.example.demo.entity.Token;
import com.example.demo.entity.TokenLog;
import com.example.demo.repository.TokenLogRepository;
import com.example.demo.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenLogServiceImpl {

    private final TokenLogRepository logRepo;
    private final TokenRepository tokenRepo;

    public TokenLogServiceImpl(TokenLogRepository l, TokenRepository t) { this.logRepo=l; this.tokenRepo=t; }

    public TokenLog addLog(Long tokenId, String message) {
        Token t = tokenRepo.findById(tokenId).orElseThrow(() -> new RuntimeException("Token not found"));
        TokenLog log = new TokenLog();
        log.setToken(t);
        log.setMessage(message);
        return logRepo.save(log);
    }

    public List<TokenLog> getLogs(Long tokenId) {
        return logRepo.findByToken_IdOrderByLoggedAtAsc(tokenId);
    }
}
