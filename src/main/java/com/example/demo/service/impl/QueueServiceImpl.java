package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.QueuePosition;
import com.example.demo.entity.Token;
import com.example.demo.repository.QueuePositionRepository;

@Service
public class QueueServiceImpl {

    @Autowired
    private QueuePositionRepository queuePositionRepository;

    // used by tests
    public QueuePosition updateQueuePosition(long tokenId, int position) {
        Token token = new Token();
        token.setId(tokenId);

        QueuePosition qp = new QueuePosition();
        qp.setToken(token);
        qp.setPosition(position);

        return queuePositionRepository.save(qp);
    }

    // alias method expected by tests
    public QueuePosition updatePosition(long tokenId, int position) {
        return updateQueuePosition(tokenId, position);
    }

    // used by tests
    public int getPosition(long tokenId) {
        return queuePositionRepository.findAll()
                .stream()
                .filter(q -> q.getToken() != null && q.getToken().getId() == tokenId)
                .map(QueuePosition::getPosition)
                .findFirst()
                .orElse(-1);
    }
}
