package com.example.demo.service;

import com.example.demo.entity.ServiceCounter;
import java.util.List;

public interface CounterService {
    List<ServiceCounter> getActiveCounters();
}
