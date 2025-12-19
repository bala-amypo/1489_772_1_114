package com.example.demo.service.impl;

import com.example.demo.entity.TokenLog;
import com.example.demo.repository.TokenLogRepository;
import com.example.demo.service.TokenLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenLogServiceImpl implements TokenLogService {

    @Autowired
    private TokenLogRepository tokenLogRepository;

    @Override
    public TokenLog createTokenLog(TokenLog tokenLog) {
        return tokenLogRepository.save(tokenLog);
    }

    @Override
    public List<TokenLog> getAllTokenLogs() {
        return tokenLogRepository.findAll();
    }

    @Override
    public TokenLog getTokenLogById(Long id) {
        return tokenLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TokenLog not found with id: " + id));
    }

    @Override
    public List<TokenLog> getTokenLogsByTokenId(Long tokenId) {
        return tokenLogRepository.findByToken_Id(tokenId);
    }
}
