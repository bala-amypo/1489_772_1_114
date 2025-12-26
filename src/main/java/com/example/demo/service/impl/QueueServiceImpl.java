package com.example.demo.service.impl;

import com.example.demo.entity.QueuePosition;
import com.example.demo.entity.Token;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service   // 🔴 THIS IS REQUIRED
public class QueueServiceImpl implements QueueService {

    @Autowired
    private QueuePositionRepository queueRepo;

    @Autowired
    private TokenRepository tokenRepo;

    @Override
    public QueuePosition addToQueue(Long tokenId) {
        Token token = tokenRepo.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));

        QueuePosition qp = new QueuePosition();
        qp.setToken(token);
        qp.setPosition((int) (queueRepo.count() + 1));

        return queueRepo.save(qp);
    }

    @Override
    public QueuePosition getQueuePosition(Long tokenId) {
        return queueRepo.findByTokenId(tokenId)
                .orElseThrow(() -> new RuntimeException("Queue position not found"));
    }
}
