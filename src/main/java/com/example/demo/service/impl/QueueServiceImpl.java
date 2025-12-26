package com.example.demo.service.impl;

import com.example.demo.entity.Queue;
import com.example.demo.repository.QueueRepository;
import com.example.demo.service.QueueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueueServiceImpl implements QueueService {

    private final QueueRepository queueRepository;

    public QueueServiceImpl(QueueRepository queueRepository) {
        this.queueRepository = queueRepository;
    }

    @Override
    public Queue createQueue(Queue queue) {
        return queueRepository.save(queue);
    }

    @Override
    public List<Queue> getAllQueues() {
        return queueRepository.findAll();
    }

    @Override
    public Queue getQueueById(Long id) {
        return queueRepository.findById(id).orElse(null);
    }
}
