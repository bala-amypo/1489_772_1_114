package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.QueuePosition;
import com.example.demo.service.QueuePositionService;

@RestController
@RequestMapping("/queue")
public class QueuePositionController {

    private final QueueService service;

    public QueueController(QueueService service) {
        this.service = service;
    }

    @PostMapping
    public QueueEntity create(@RequestBody QueueEntity q) {
        return service.save(q);
    }
}
