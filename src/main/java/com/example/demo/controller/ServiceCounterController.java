package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.ServiceCounter;
import com.example.demo.service.ServiceCounterService;

@RestController
@RequestMapping("/counter")
public class ServiceCounterController {

    private final ServiceCounterService service;

    public ServiceCounterController(ServiceCounterService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ServiceCounter add(@RequestBody ServiceCounter counter) {
        return service.addCounter(counter);
    }

    @GetMapping("/all")
    public List<ServiceCounter> all() {
        return service.getAllCounters();
    }
}
