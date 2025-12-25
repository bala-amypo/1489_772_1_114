package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import java.util.List;

public class TokenLogServiceImpl {

    private final TokenLogRepository repo;
    private final TokenRepository tokenRepo;

    public TokenLogServiceImpl(TokenLogRepository r, TokenRepository t) {
        this.repo = r;
        this.tokenRepo = t;
    }

    public TokenLog addLog(Long tokenId, String msg) {
        Token t = tokenRepo.findById(tokenId).orElseThrow();
        TokenLog l = new TokenLog();
        l.setToken(t);
        l.setLogMessage(msg);
        return repo.save(l);
    }

    public List<TokenLog> getLogs(Long tokenId) {
        return repo.findByToken_IdOrderByLoggedAtAsc(tokenId);
    }
}
