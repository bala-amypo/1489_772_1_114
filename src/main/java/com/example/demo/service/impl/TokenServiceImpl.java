package com.example.demo.service.impl;

import com.example.demo.entity.Token;
import com.example.demo.entity.ServiceCounter;
import com.example.demo.entity.TokenLog;
import com.example.demo.entity.Queue;

import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.ServiceCounterRepository;
import com.example.demo.repository.TokenLogRepository;
import com.example.demo.repository.QueueRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenServiceImpl {

    private final TokenRepository tokenRepo;
    private final ServiceCounterRepository counterRepo;
    private final TokenLogRepository logRepo;
    private final QueueRepository queueRepo;

    public TokenServiceImpl(
            TokenRepository tokenRepo,
            ServiceCounterRepository counterRepo,
            TokenLogRepository logRepo,
            QueueRepository queueRepo
    ) {
        this.tokenRepo = tokenRepo;
        this.counterRepo = counterRepo;
        this.logRepo = logRepo;
        this.queueRepo = queueRepo;
    }

    public Token issueToken(Long counterId) {
        ServiceCounter sc = counterRepo.findById(counterId)
                .orElseThrow(() -> new RuntimeException("Counter not found"));

        if (!Boolean.TRUE.equals(sc.getIsActive())) {
            throw new IllegalArgumentException("Counter not active");
        }

        Token token = new Token();
        token.setServiceCounter(sc);
        token.setStatus("WAITING");
        token.setIssuedAt(LocalDateTime.now());
        token.setTokenNumber(sc.getCounterName() + "-" + System.currentTimeMillis());

        Token savedToken = tokenRepo.save(token);

        Queue queue = new Queue();
        queue.setToken(savedToken);
        queue.setPosition(
                tokenRepo
                        .findByServiceCounter_IdAndStatusOrderByIssuedAtAsc(counterId, "WAITING")
                        .size()
        );

        queueRepo.save(queue);

        TokenLog log = new TokenLog();
        log.setToken(savedToken);
        log.setLogMessage("Issued");
        logRepo.save(log);

        return savedToken;
    }

    public Token updateStatus(Long tokenId, String status) {
        Token token = tokenRepo.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));

        token.setStatus(status);
        token.setCompletedAt(LocalDateTime.now());
        tokenRepo.save(token);

        TokenLog log = new TokenLog();
        log.setToken(token);
        log.setLogMessage(status);
        logRepo.save(log);

        return token;
    }

    public Token getToken(Long id) {
        return tokenRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Token not found"));
    }
}
