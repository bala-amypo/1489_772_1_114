package com.example.demo.service.impl;

import com.example.demo.entity.QueuePosition;
import com.example.demo.entity.Token;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QueueServiceImpl implements QueueService {
    
    @Autowired
    private QueuePositionRepository queueRepository;
    
    @Autowired
    private TokenRepository tokenRepository;
    
    public QueueServiceImpl(QueuePositionRepository queueRepository, TokenRepository tokenRepository) {
        this.queueRepository = queueRepository;
        this.tokenRepository = tokenRepository;
    }
    
    @Override
    public QueuePosition updateQueuePosition(Long tokenId, Integer position) {
        if (position < 1) {
            throw new IllegalArgumentException("Position must be >= 1");
        }
        
        Token token = tokenRepository.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));
        
        QueuePosition queuePosition;
        Optional<QueuePosition> existing = queueRepository.findByToken_Id(tokenId);
        
        if (existing.isPresent()) {
            queuePosition = existing.get();
            queuePosition.setPosition(position);
        } else {
            queuePosition = new QueuePosition();
            queuePosition.setToken(token);
            queuePosition.setPosition(position);
        }
        
        return queueRepository.save(queuePosition);
    }
    
    @Override
    public QueuePosition getPosition(Long tokenId) {
        return queueRepository.findByToken_Id(tokenId)
                .orElseThrow(() -> new RuntimeException("Queue position not found"));
    }
}