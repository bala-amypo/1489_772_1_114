package com.example.demo.controller;

import com.example.demo.entity.QueuePosition;
import com.example.demo.service.QueuePositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/queue-positions")
public class QueuePositionController {

    @Autowired
    private QueuePositionService queueService;

    @PostMapping
    public QueuePosition addQueuePosition(@RequestBody QueuePosition queue) {
        return queueService.saveQueuePosition(queue);
    }

    @GetMapping
    public List<QueuePosition> getAllQueuePositions() {
        return queueService.getAllQueuePositions();
    }

    @GetMapping("/{id}")
    public QueuePosition getQueueById(@PathVariable Long id) {
        return queueService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteQueue(@PathVariable Long id) {
        queueService.deleteById(id);
    }
}
