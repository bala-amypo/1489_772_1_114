package com.example.demo.controller;

import com.example.demo.entity.ServiceCounter;
import com.example.demo.service.ServiceCounterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-counters")
public class ServiceCounterController {

    private final ServiceCounterService service;

    public ServiceCounterController(ServiceCounterService service) {
        this.service = service;
    }

    @PostMapping
    public ServiceCounter create(@RequestBody ServiceCounter counter) {
        return service.create(counter);
    }

    @GetMapping
    public List<ServiceCounter> getAll() {
        return service.getAll();
    }
}
