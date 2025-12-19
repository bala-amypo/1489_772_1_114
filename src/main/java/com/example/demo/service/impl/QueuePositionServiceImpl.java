package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.QueuePosition;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.service.QueuePositionService;

@Service
public class QueuePositionServiceImpl implements QueuePositionService {

    @Autowired
    private QueuePositionRepository repo;

    @Override
    public QueuePosition saveQueuePosition(QueuePosition qp) {
        return repo.save(qp);
    }

    @Override
    public List<QueuePosition> getAllQueuePositions() {
        return repo.findAll();
    }

    @Override
    public QueuePosition getById(Long id) {
        return repo.findById(id).orElse(null);
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
