package com.example.demo.service;

import com.example.demo.entity.QueuePosition;
import java.util.List;

public interface QueuePositionService {
    QueuePosition saveQueuePosition(QueuePosition queue);
    List<QueuePosition> getAllQueuePositions();
    QueuePosition getById(Long id);
    void deleteById(Long id);
    QueuePosition getByTokenId(Long tokenId); // must be implemented
}
