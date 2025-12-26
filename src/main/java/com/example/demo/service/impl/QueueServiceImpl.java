package com.example.demo.service.impl;

import com.example.demo.entity.QueuePosition;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.service.QueueService;
import org.springframework.stereotype.Service;

@Service
public class QueueServiceImpl implements QueueService {

    private final QueuePositionRepository queueRepo;

    public QueueServiceImpl(QueuePositionRepository queueRepo) {
        this.queueRepo = queueRepo;
    }

    @Override
    public QueuePosition add(QueuePosition qp) {
        return queueRepo.save(qp);
    }

    @Override
    public QueuePosition getByTokenId(Long tokenId) {
        return queueRepo.findByToken_Id(tokenId)
                .orElseThrow(() -> new RuntimeException("Queue not found"));
    }

    @Override
    public int getPosition(Long tokenId) {
        return getByTokenId(tokenId).getPosition();
    }

    @Override
    public QueuePosition updatePosition(Long tokenId, int position) {
        QueuePosition qp = getByTokenId(tokenId);
        qp.setPosition(position);
        return queueRepo.save(qp);
    }
}
