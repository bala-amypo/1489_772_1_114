package com.example.demo.service.impl;

import com.example.demo.entity.QueuePosition;
import com.example.demo.entity.Token;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.QueuePositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueuePositionServiceImpl implements QueuePositionService {

    @Autowired
    private QueuePositionRepository repo;

    @Autowired
    private TokenRepository tokenRepo;

    @Override
    public QueuePosition saveQueuePosition(QueuePosition queuePosition) {
        // Link Token entity if token is provided
        if (queuePosition.getToken() != null && queuePosition.getToken().getId() != null) {
            Token token = tokenRepo.findById(queuePosition.getToken().getId())
                    .orElseThrow(() -> new RuntimeException("Token not found"));
            queuePosition.setToken(token);
        }
        return repo.save(queuePosition);
    }

    @Override
    public List<QueuePosition> getAllQueuePositions() {
        return repo.findAll();
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
    public QueuePosition updateQueuePosition(Long id, QueuePosition updatedQueue) {
        QueuePosition existingQueue = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("QueuePosition not found"));

        existingQueue.setPosition(updatedQueue.getPosition());

        if (updatedQueue.getToken() != null && updatedQueue.getToken().getId() != null) {
            Token token = tokenRepo.findById(updatedQueue.getToken().getId())
                    .orElseThrow(() -> new RuntimeException("Token not found"));
            existingQueue.setToken(token);
        }

        return repo.save(existingQueue);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
