package com.example.demo.service.impl;

import com.example.demo.entity.ServiceCounter;
import com.example.demo.repository.ServiceCounterRepository;
import com.example.demo.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounterServiceImpl implements CounterService {

    @Autowired
    private ServiceCounterRepository counterRepository;

    @Override
    public List<ServiceCounter> getActiveCounters() {
        return counterRepository.findByActiveTrue();
    }
}
