package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.ServiceCounter;

public interface ServiceCounterService {

    ServiceCounter save(ServiceCounter counter);

    List<ServiceCounter> getAllActiveCounters();

    ServiceCounter getById(Long id);

    void delete(Long id);
}
