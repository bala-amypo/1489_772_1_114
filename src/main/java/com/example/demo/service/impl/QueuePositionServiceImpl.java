package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.QueuePosition;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.service.QueuePositionService;

@Service
public class QueuePositionServiceImpl implements QueuePositionService {

    @Autowired
    private QueuePositionRepository repo;

    @Autowired
    private TokenRepository tokenRepo;

    @Override
    public QueuePosition saveQueuePosition(QueuePosition qp) {

        Token token = tokenRepo.findById(qp.getTokenId())
                .orElseThrow(() -> new RuntimeException("Token not found"));

        qp.setToken(token);
        return repo.save(qp);
    }

    // other methods unchanged
}
