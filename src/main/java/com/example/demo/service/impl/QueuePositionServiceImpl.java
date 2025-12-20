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
    private QueuePositionRepository queuePositionRepo;

    @Override
    public QueuePosition getById(Long id) {
        return queuePositionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("QueuePosition not found"));
    }

    @Override
    public QueuePosition getByTokenId(Long tokenId) {
        return queuePositionRepo.findByToken_Id(tokenId);
    }

    @Override
    public List<QueuePosition> getAllPositions() {
        return queuePositionRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        queuePositionRepo.deleteById(id);
    }

    @Override
    public QueuePosition savePosition(QueuePosition position) {
        return queuePositionRepo.save(position);
    }
}
