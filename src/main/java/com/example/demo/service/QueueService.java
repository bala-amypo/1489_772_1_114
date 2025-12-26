package com.example.demo.service;

import com.example.demo.entity.QueuePosition;

public interface QueueService {

    QueuePosition add(QueuePosition queuePosition);

    QueuePosition getByTokenId(Long tokenId);

    int getPosition(Long tokenId);

    QueuePosition updatePosition(Long tokenId, int position);
}
