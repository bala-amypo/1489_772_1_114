package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.QueuePosition;

public interface QueuePositionService {

    QueuePosition saveQueuePosition(QueuePosition qp);

    List<QueuePosition> getAllQueuePositions();

    QueuePosition getById(Long id);

    QueuePosition getByTokenId(Long tokenId);

    void deleteById(Long id);
}

public interface QueuePositionService {
    QueuePosition createQueue(Long tokenId, Integer position);
}
