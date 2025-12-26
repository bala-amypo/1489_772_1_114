package com.example.demo.service;

import com.example.demo.entity.Queue;

public interface QueueService {

    Queue createQueue(Queue queue);

    Queue getQueue(Long id);

    Queue updateStatus(Long id, String status);
}
