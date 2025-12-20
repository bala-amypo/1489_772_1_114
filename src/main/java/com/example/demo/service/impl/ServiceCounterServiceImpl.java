package com.example.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.ServiceCounter;
import com.example.demo.repository.ServiceCounterRepository;
import com.example.demo.service.ServiceCounterService;

@Service
public class ServiceCounterServiceImpl implements ServiceCounterService {

    @Autowired
    private ServiceCounterRepository repo;

    @Override
    public ServiceCounter save(ServiceCounter counter) {
        return repo.save(counter);
    }

    @Override
    public List<ServiceCounter> getAllActiveCounters() {
        return repo.findByIsActiveTrue();
    }

    @Override
    public ServiceCounter getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("ServiceCounter not found with id: " + id)
                );
    }

    @Override
    public void delete(Long id) {
        ServiceCounter counter = repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("ServiceCounter not found with id: " + id)
                );
        repo.delete(counter);
    }
}
