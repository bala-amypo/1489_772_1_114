package com.example.demo.service.impl;

import com.example.demo.entity.ServiceCounter;
import com.example.demo.repository.ServiceCounterRepository;
import com.example.demo.service.ServiceCounterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // 🔴 THIS ANNOTATION IS REQUIRED
public class ServiceCounterServiceImpl implements ServiceCounterService {

    private final ServiceCounterRepository repo;

    public ServiceCounterServiceImpl(ServiceCounterRepository repo) {
        this.repo = repo;
    }

    @Override
    public ServiceCounter create(ServiceCounter counter) {
        return repo.save(counter);
    }

    @Override
    public List<ServiceCounter> getAll() {
        return repo.findAll();
    }

    @Override
    public ServiceCounter getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("ServiceCounter not found"));
    }

    @Override
    public ServiceCounter update(Long id, ServiceCounter counter) {
        ServiceCounter existing = getById(id);
        existing.setCounterName(counter.getCounterName());
        existing.setIsActive(counter.getIsActive());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
