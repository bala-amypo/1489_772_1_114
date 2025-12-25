package com.example.demo.controller;

import com.example.demo.entity.ServiceCounter;
import com.example.demo.service.ServiceCounterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/counters")
@Tag(name = "Service Counters")
public class ServiceCounterController {

    private final ServiceCounterService service;

    public ServiceCounterController(ServiceCounterService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Add service counter")
    public ServiceCounter add(@RequestBody ServiceCounter counter) {
        return service.addCounter(counter);
    }

    @GetMapping("/active")
    @Operation(summary = "Get active counters")
    public List<ServiceCounter> getActive() {
        return service.getActiveCounters();
    }
}
