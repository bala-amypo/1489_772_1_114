package com.example.demo.controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.ServiceCounter;
import com.example.demo.service.ServiceCounterService;

@RestController
@RequestMapping("/api/counters")
public class ServiceCounterController {

    @Autowired
    private ServiceCounterService service;

    @PostMapping
    public ServiceCounter createCounter(@Valid @RequestBody ServiceCounter counter) {
        return service.save(counter);
    }

    @GetMapping("/active")
    public List<ServiceCounter> getActiveCounters() {
        return service.getAllActiveCounters();
    }

    @GetMapping("/{id}")
    public ServiceCounter getCounterById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCounter(@PathVariable Long id) {
        service.delete(id);
    }
}
