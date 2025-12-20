package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.demo.entity.QueuePosition;
import com.example.demo.repository.QueuePositionRepository;
import com.example.demo.service.QueuePositionService;

@Service
public class QueuePositionServiceImpl implements QueuePositionService {

    @Autowired
    private QueuePositionRepository repo;

    @Override
    public QueuePosition save(QueuePosition position) {
        return repo.save(position);
    }

    @Override
    public List<QueuePosition> getAll() {
        return repo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
