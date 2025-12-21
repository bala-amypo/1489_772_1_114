package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.QueuePosition;
import com.example.demo.service.QueuePositionService;

@RestController
@RequestMapping("/queue")
public class QueuePositionController {

    private final QueuePositionService service;

    public QueuePositionController(QueuePositionService service) {
        this.service = service;
    }

    @PostMapping
    public QueuePosition create(@RequestBody QueuePosition q) {
        return service.save(q);
    }
}
