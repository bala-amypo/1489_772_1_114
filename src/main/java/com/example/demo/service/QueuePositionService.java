package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.entity.QueuePosition;
import com.example.demo.repository.QueuePositionRepository;
import java.time.LocalDateTime;

@Service
public class QueuePositionService {

    private final QueuePositionRepository repo;

    public QueuePositionService(QueuePositionRepository repo) {
        this.repo = repo;
    }

    public QueuePosition  save(QueuePosition q) {
        q.setUpdatedAt(LocalDateTime.now());
        return repo.save(q);
    }
}
