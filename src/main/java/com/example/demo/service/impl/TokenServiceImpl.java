package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TokenServiceImpl {

    private final TokenRepository tokenRepo;
    private final ServiceCounterRepository counterRepo;
    private final TokenLogRepository logRepo;
    private final QueuePositionRepository queueRepo;

    public TokenServiceImpl(TokenRepository t, ServiceCounterRepository c, TokenLogRepository l, QueuePositionRepository q) {
        this.tokenRepo=t; this.counterRepo=c; this.logRepo=l; this.queueRepo=q;
    }

    public Token issueToken(Long counterId) {
        ServiceCounter sc = counterRepo.findById(counterId).orElseThrow(() -> new RuntimeException("Counter not found"));
        if(!sc.getIsActive()) throw new IllegalArgumentException("Counter not active");

        Token t = new Token();
        t.setServiceCounter(sc);
        t.setStatus("WAITING");
        t.setTokenNumber(sc.getCounterName()+"-"+(int)(Math.random()*1000));
        t = tokenRepo.save(t);

        // Queue position
        QueuePosition qp = new QueuePosition();
        qp.setToken(t);
        qp.setPosition(1);
        queueRepo.save(qp);

        // Log
        TokenLog log = new TokenLog();
        log.setToken(t);
        log.setMessage("Token issued");
        logRepo.save(log);

        return t;
    }

    public Token updateStatus(Long tokenId, String status) {
        Token t = tokenRepo.findById(tokenId).orElseThrow(() -> new RuntimeException("Token not found"));

        if(status.equals("SERVING") && !t.getStatus().equals("WAITING")) throw new IllegalArgumentException("Invalid status");
        if(status.equals("COMPLETED") && !t.getStatus().equals("SERVING")) throw new IllegalArgumentException("Invalid status");
        if(status.equals("CANCELLED") && t.getStatus().equals("WAITING")) {
            t.setCompletedAt(LocalDateTime.now());
        }
        t.setStatus(status);
        if(status.equals("COMPLETED") || status.equals("CANCELLED")) t.setCompletedAt(LocalDateTime.now());
        tokenRepo.save(t);

        TokenLog log = new TokenLog();
        log.setToken(t);
        log.setMessage("Status updated to "+status);
        logRepo.save(log);

        return t;
    }

    public Token getToken(Long id) { return tokenRepo.findById(id).orElseThrow(() -> new RuntimeException("Token not found")); }
}
