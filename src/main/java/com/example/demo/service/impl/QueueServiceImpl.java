package com.example.demo.service.impl;

import com.example.demo.entity.QueuePosition;
import com.example.demo.entity.Token;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.repository.TokenRepository;
import org.springframework.stereotype.Service;

@Service
public class QueueServiceImpl {

    private final QueuePositionRepository queueRepo;
    private final TokenRepository tokenRepo;

    public QueueServiceImpl(QueuePositionRepository q, TokenRepository t) { this.queueRepo=q; this.tokenRepo=t; }

    public QueuePosition updateQueuePosition(Long tokenId, Integer pos) {
        if(pos < 1) throw new IllegalArgumentException("Position must be >= 1");
        Token t = tokenRepo.findById(tokenId).orElseThrow(() -> new RuntimeException("Token not found"));
        QueuePosition qp = new QueuePosition();
        qp.setToken(t);
        qp.setPosition(pos);
        return queueRepo.save(qp);
    }

    public QueuePosition getPosition(Long tokenId) {
        return queueRepo.findByToken_Id(tokenId).orElseThrow(() -> new RuntimeException("Queue position not found"));
    }
}
