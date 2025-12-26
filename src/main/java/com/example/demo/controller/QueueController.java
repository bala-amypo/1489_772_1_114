package com.example.demo.controller;

import com.example.demo.entity.Queue;
import com.example.demo.service.QueueService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/queue")
public class QueueController {

    private final QueueService queueService;

    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    // ✅ Create Queue
    @PostMapping
    public Queue createQueue(@RequestBody Queue queue) {
        return queueService.createQueue(queue);
    }

    // ✅ Get all queues
    @GetMapping
    public List<Queue> getAllQueues() {
        return queueService.getAllQueues();
    }

    // ✅ Get queue by id
    @GetMapping("/{id}")
    public Queue getQueueById(@PathVariable Long id) {
        return queueService.getQueueById(id);
    }
}
