package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.QueuePosition;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.QueuePositionService;
import com.example.demo.entity.Token;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class QueuePositionServiceImpl implements QueuePositionService {

    private final QueuePositionRepository queueRepo;
    private final TokenRepository tokenRepo;

    @Autowired
    public QueuePositionServiceImpl(QueuePositionRepository queueRepo, TokenRepository tokenRepo) {
        this.queueRepo = queueRepo;
        this.tokenRepo = tokenRepo;
    }

    @Override
    public QueuePosition createQueuePosition(QueuePosition queue) {
        Token token = tokenRepo.findById(queue.getToken().getId())
                .orElseThrow(() -> new RuntimeException("Token not found"));
        queue.setToken(token);
        queue.setUpdatedAt(LocalDateTime.now());
        return queueRepo.save(queue);
    }

    @Override
    public QueuePosition getQueuePosition(Long id) {
        return queueRepo.findById(id).orElseThrow(() -> new RuntimeException("QueuePosition not found"));
    }

    @Override
    public List<QueuePosition> getAllQueuePositions() {
        return queueRepo.findAll();
    }

    @Override
    public QueuePosition updateQueuePosition(Long id, QueuePosition queue) {
        QueuePosition existing = getQueuePosition(id);
        existing.setPosition(queue.getPosition());
        existing.setUpdatedAt(LocalDateTime.now());
        return queueRepo.save(existing);
    }

    @Override
    public void deleteQueuePosition(Long id) {
        queueRepo.deleteById(id);
    }
}
