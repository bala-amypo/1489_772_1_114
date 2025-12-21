package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.QueuePosition;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.service.QueuePositionService;

@Service
public class QueuePositionServiceImpl implements QueuePositionService {

    private final QueuePositionRepository repository;

    public QueuePositionServiceImpl(QueuePositionRepository repository) {
        this.repository = repository;
    }

    @Override
    public QueuePosition create(QueuePosition queuePosition) {
        queuePosition.setUpdatedAt(LocalDateTime.now());
        return repository.save(queuePosition);
    }

    @Override
    public List<QueuePosition> getAll() {
        return repository.findAll();
    }

    @Override
    public QueuePosition getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public QueuePosition update(Long id, QueuePosition queuePosition) {
        QueuePosition existing = repository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }

        existing.setTokenId(queuePosition.getTokenId());
        existing.setPosition(queuePosition.getPosition());
        existing.setUpdatedAt(LocalDateTime.now());

        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
