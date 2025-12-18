package com.example.demo.service;

import com.example.demo.entity.QueuePosition;
import com.example.demo.repository.QueuePositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueuePositionServiceImpl implements QueuePositionService {

    private final QueuePositionRepository repo;

    public QueuePositionServiceImpl(QueuePositionRepository repo) {
        this.repo = repo;
    }

    @Override
    public QueuePosition createQueuePosition(QueuePosition queuePosition) {
        return repo.save(queuePosition);
    }

    @Override
    public List<QueuePosition> getAllQueuePositions() {
        return repo.findAll();
    }

    @Override
    public QueuePosition getQueuePositionById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public QueuePosition updateQueuePosition(Long id, QueuePosition queuePosition) {
        QueuePosition existing = repo.findById(id).orElse(null);
        if (existing != null) {
            existing.setTokenId(queuePosition.getTokenId());
            existing.setPosition(queuePosition.getPosition());
            existing.setStatus(queuePosition.getStatus());
            return repo.save(existing);
        }
        return null;
    }

    @Override
    public void deleteQueuePosition(Long id) {
        repo.deleteById(id);
    }
}
