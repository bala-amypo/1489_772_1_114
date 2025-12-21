package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.QueuePosition;
import com.example.demo.service.QueuePositionService;
import java.util.List;

@RestController
@RequestMapping("/queue")
public class QueuePositionController {

    private final QueuePositionService queueService;

    @Autowired
    public QueuePositionController(QueuePositionService queueService) {
        this.queueService = queueService;
    }

    @PostMapping
    public QueuePosition createQueue(@RequestBody QueuePosition queue) {
        return queueService.createQueuePosition(queue);
    }

    @GetMapping("/{id}")
    public QueuePosition getQueue(@PathVariable Long id) {
        return queueService.getQueuePosition(id);
    }

    @GetMapping
    public List<QueuePosition> getAllQueues() {
        return queueService.getAllQueuePositions();
    }

    @PutMapping("/{id}")
    public QueuePosition updateQueue(@PathVariable Long id, @RequestBody QueuePosition queue) {
        return queueService.updateQueuePosition(id, queue);
    }

    @DeleteMapping("/{id}")
    public void deleteQueue(@PathVariable Long id) {
        queueService.deleteQueuePosition(id);
    }
}
