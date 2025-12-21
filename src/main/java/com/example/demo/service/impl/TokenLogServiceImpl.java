package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.TokenLog;
import com.example.demo.repository.TokenLogRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.TokenLogService;
import com.example.demo.entity.Token;
import java.util.List;

@Service
public class TokenLogServiceImpl implements TokenLogService {

    private final TokenLogRepository logRepo;
    private final TokenRepository tokenRepo;

    @Autowired
    public TokenLogServiceImpl(TokenLogRepository logRepo, TokenRepository tokenRepo) {
        this.logRepo = logRepo;
        this.tokenRepo = tokenRepo;
    }

    @Override
    public TokenLog createLog(TokenLog log) {
        Token token = tokenRepo.findById(log.getToken().getId())
                .orElseThrow(() -> new RuntimeException("Token not found"));
        log.setToken(token);
        return logRepo.save(log);
    }

    @Override
    public TokenLog getLog(Long id) {
        return logRepo.findById(id).orElseThrow(() -> new RuntimeException("TokenLog not found"));
    }

    @Override
    public List<TokenLog> getAllLogs() {
        return logRepo.findAll();
    }

    @Override
    public List<TokenLog> getLogsByToken(Long tokenId) {
        return logRepo.findByToken_IdOrderByLoggedAtAsc(tokenId);
    }

    @Override
    public void deleteLog(Long id) {
        logRepo.deleteById(id);
    }
}
