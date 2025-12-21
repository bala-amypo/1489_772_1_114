package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.entity.Token;
import com.example.demo.entity.TokenLog;
import com.example.demo.repository.TokenLogRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.TokenLogService;

@Service
public class TokenLogServiceImpl implements TokenLogService {

    @Autowired
    private TokenLogRepository repo;

    @Autowired
    private TokenRepository tokenRepo;

    @Override
    public TokenLog save(TokenLog log) {
        Token token = tokenRepo.findById(log.getToken().getId())
                .orElseThrow(() -> new RuntimeException("Token not found"));

        log.setToken(token);
        log.setLoggedAt(LocalDateTime.now());
        return repo.save(log);
    }

    @Override
    public List<TokenLog> getLogsByTokenId(Long tokenId) {
        return repo.findByToken_IdOrderByLoggedAtAsc(tokenId);
    }
}
