package com.example.demo.service;

import com.example.demo.entity.QueuePosition;
import java.util.List;

public interface QueuePositionService {

    QueuePosition saveQueuePosition(QueuePosition queuePosition);

    List<QueuePosition> getAllQueuePositions();

    QueuePosition getById(Long id);

    QueuePosition getByTokenId(Long tokenId);

    QueuePosition updateQueuePosition(Long id, QueuePosition queuePosition);

    void deleteById(Long id);
}
