package com.example.demo.service;

import com.example.demo.entity.ServiceCounter;

import java.util.List;

public interface ServiceCounterService {

    ServiceCounter create(ServiceCounter counter);

    List<ServiceCounter> getAll();

    ServiceCounter getById(Long id);

    ServiceCounter update(Long id, ServiceCounter counter);

    void delete(Long id);
}
