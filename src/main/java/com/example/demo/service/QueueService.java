package com.example.demo.service;

import com.example.demo.entity.QueuePosition;

public interface QueueService {

    QueuePosition addToQueue(Long tokenId);

    QueuePosition getQueuePosition(Long tokenId);
}
