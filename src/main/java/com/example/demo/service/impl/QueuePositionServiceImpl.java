package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

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
    public QueuePosition saveQueuePosition(QueuePosition position) {
        Token token = tokenRepo.findById(position.getTokenId())
                .orElseThrow(() -> new RuntimeException("Token not found"));
        position.setToken(token);
        return repo.save(position);
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
