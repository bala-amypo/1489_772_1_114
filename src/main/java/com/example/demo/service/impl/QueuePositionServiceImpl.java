package com.example.demo.service.impl;

import com.example.demo.entity.QueuePosition;
import com.example.demo.entity.Token;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.service.QueuePositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueuePositionServiceImpl implements QueuePositionService {

    @Autowired
    private QueuePositionRepository queuePositionRepository;

    @Override
    public QueuePosition save(QueuePosition position) {
        if (position.getPosition() < 1) {
            throw new IllegalArgumentException("Invalid position");
        }
        return queuePositionRepository.save(position);
    }

    @Override
    public QueuePosition findByToken(Token token) {
        return queuePositionRepository.findByToken(token).orElse(null);
    }
}
