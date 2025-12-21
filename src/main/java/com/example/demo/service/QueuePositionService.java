package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.QueuePosition;

public interface QueuePositionService {

    QueuePosition create(QueuePosition queuePosition);

    List<QueuePosition> getAll();

    QueuePosition getById(Long id);

    QueuePosition update(Long id, QueuePosition queuePosition);

    void delete(Long id);
}
