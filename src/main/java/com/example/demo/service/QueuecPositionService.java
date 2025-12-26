package com.example.demo.service;

import com.example.demo.entity.QueuePosition;
import com.example.demo.entity.Token;

public interface QueuePositionService {
    QueuePosition save(QueuePosition position);
    QueuePosition findByToken(Token token);
}
