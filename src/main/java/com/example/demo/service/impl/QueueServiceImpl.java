package com.example.demo.service.impl;

import com.example.demo.entity.Queue;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.QueueRepository;
import com.example.demo.service.QueueService;
import org.springframework.stereotype.Service;

@Service   // ✅ THIS IS REQUIRED
public class QueueServiceImpl implements QueueService {

    private final QueueRepository queueRepository;

    // ✅ SINGLE constructor
    public QueueServiceImpl(QueueRepository queueRepository) {
        this.queueRepository = queueRepository;
    }

    @Override
    public Queue createQueue(Queue queue) {
        return queueRepository.save(queue);
    }

    @Override
    public Queue getQueue(Long id) {
        return queueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Queue not found"));
    }

    @Override
    public Queue updateStatus(Long id, String status) {
        Queue queue = getQueue(id);
        queue.setStatus(status);
        return queueRepository.save(queue);
    }
}
