
package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.example.demo.entity.TokenLog;
import com.example.demo.repository.TokenLogRepository;
import com.example.demo.service.TokenLogService;

@Service
public class TokenLogServiceImpl implements TokenLogService {

    @Autowired
    private TokenLogRepository repo;

    @Override
    public TokenLog createLog(TokenLog tokenLog) {
        return repo.save(tokenLog);
    }

    @Override
    public List<TokenLog> getLogsByTokenId(Long tokenId) {
        return repo.findByTokenId(tokenId);
    }
}
@Service
public class TokenLogServiceImpl implements TokenLogService {

    @Autowired
    private TokenLogRepository repo;

    @Autowired
    private TokenRepository tokenRepo;

    @Override
    public TokenLog saveLog(TokenLog log) {

        Token token = tokenRepo.findById(log.getTokenId())
                .orElseThrow(() -> new RuntimeException("Token not found"));

        log.setToken(token);
        return repo.save(log);
    }

    @Override
    public List<TokenLog> getLogsByTokenId(Long tokenId) {
        return repo.findByToken_IdOrderByLoggedAtAsc(tokenId);
    }
}
