package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private TokenRepository tokenRepository;

    @Override
    public QueuePosition saveQueuePosition(QueuePosition qp) {

        Token token = tokenRepository.findById(qp.getTokenId())
                .orElseThrow(() ->
                        new RuntimeException("Token not found with id: " + qp.getTokenId())
                );

        qp.setToken(token);
        return repo.save(qp);
    }

    @Override
    public List<QueuePosition> getAllQueuePositions() {
        return repo.findAll();
    }

    @Override
    public QueuePosition getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("QueuePosition not found with id: " + id)
                );
    }

    @Override
    public QueuePosition getByTokenId(Long tokenId) {
        QueuePosition qp = repo.findByToken_Id(tokenId);
        if (qp == null) {
            throw new RuntimeException("QueuePosition not found for token id: " + tokenId);
        }
        return qp;
    }

    @Override
    public void deleteById(Long id) {
        repo.delete(getById(id));
    }
}
