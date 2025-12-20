package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.example.demo.entity.QueuePosition;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.service.QueuePositionService;

@Service
public class QueuePositionServiceImpl implements QueuePositionService {

    @Autowired
    private QueuePositionRepository repo;

    @Override
    public QueuePosition saveQueuePosition(QueuePosition queuePosition) {
        return repo.save(queuePosition);
    }

    @Override
    public List<QueuePosition> getAllQueuePositions() {
        return repo.findAll();
    }

    @Override
    public QueuePosition getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("QueuePosition not found"));
    }

    @Override
    public QueuePosition getByTokenId(Long tokenId) {
        return repo.findByToken_Id(tokenId);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
