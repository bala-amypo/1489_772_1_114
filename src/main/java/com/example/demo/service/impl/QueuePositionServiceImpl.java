package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.entity.QueuePosition;
import com.example.demo.entity.Token;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.service.QueuePositionService;

@Service
public class QueuePositionServiceImpl implements QueuePositionService {

    @Autowired
    private QueuePositionRepository repo;

    @Autowired
    private TokenRepository tokenRepo;

    @Override
    public QueuePosition save(QueuePosition qp) {
        Token token = tokenRepo.findById(qp.getToken().getId())
                .orElseThrow(() -> new RuntimeException("Token not found"));

        qp.setToken(token);
        qp.setUpdatedAt(LocalDateTime.now());
        return repo.save(qp);
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
    public List<QueuePosition> getAllQueuePositions() {
        return repo.findAll();
    }
}
