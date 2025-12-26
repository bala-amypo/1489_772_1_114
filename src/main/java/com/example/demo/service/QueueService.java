package com.example.demo.service;

import com.example.demo.entity.Queue;
import java.util.List;

public interface QueueService {

    Queue createQueue(Queue queue);

    List<Queue> getAllQueues();

    Queue getQueueById(Long id);
}
