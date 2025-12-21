package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.QueuePosition;
import com.example.demo.entity.Token;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.QueuePositionService;

import java.util.List;

@Service
public class QueuePositionServiceImpl implements QueuePositionService {

    @Autowired
    private QueuePositionRepository repo;

    @Autowired
    private TokenRepository tokenRepo;

    @Override
    public QueuePosition saveQueuePosition(QueuePosition queuePosition) {
        // If queuePosition has a tokenId, fetch the token and set it
        if (queuePosition.getTokenId() != null) {
            Token token = tokenRepo.findById(queuePosition.getTokenId())
                    .orElseThrow(() -> new RuntimeException("Token not found"));
            queuePosition.setToken(token);
        }
        return repo.save(queuePosition);
    }

    @Override
    public QueuePosition getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("QueuePosition not found"));
    }

    @Override
    public QueuePosition getByTokenId(Long tokenId) {
        return repo.findByToken_Id(tokenId);
    }

    @Override
    public List<QueuePosition> getAllQueuePositions() {
        return repo.findAll();
    }

    @Override
    public QueuePosition updateQueuePosition(Long id, QueuePosition updatedQueue) {
        QueuePosition existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("QueuePosition not found"));

        if (updatedQueue.getTokenId() != null) {
            Token token = tokenRepo.findById(updatedQueue.getTokenId())
                    .orElseThrow(() -> new RuntimeException("Token not found"));
            existing.setToken(token);
        }
        existing.setPosition(updatedQueue.getPosition());
        existing.setUpdatedAt(updatedQueue.getUpdatedAt());

        return repo.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        QueuePosition existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("QueuePosition not found"));
        repo.delete(existing);
    }
}
