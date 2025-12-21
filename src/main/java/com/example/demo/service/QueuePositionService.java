package com.example.demo.service;

import com.example.demo.entity.QueuePosition;
import java.util.List;

public interface QueuePositionService {
    QueuePosition createQueuePosition(QueuePosition queue);
    QueuePosition getQueuePosition(Long id);
    List<QueuePosition> getAllQueuePositions();
    QueuePosition updateQueuePosition(Long id, QueuePosition queue);
    void deleteQueuePosition(Long id);
}
