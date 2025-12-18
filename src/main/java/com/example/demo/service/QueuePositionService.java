package com.example.demo.service;

import com.example.demo.entity.QueuePosition;
import java.util.List;

public interface QueuePositionService {

    QueuePosition createQueuePosition(QueuePosition queuePosition);

    List<QueuePosition> getAllQueuePositions();

    QueuePosition getQueuePositionById(Long id);

    QueuePosition updateQueuePosition(Long id, QueuePosition queuePosition);

    void deleteQueuePosition(Long id);
}
