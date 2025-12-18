package com.example.demo.controller;

import com.example.demo.entity.QueuePosition;
import com.example.demo.service.QueuePositionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/queue-positions")
public class QueuePositionController {

    private final QueuePositionService service;

    public QueuePositionController(QueuePositionService service) {
        this.service = service;
    }

    @PostMapping
    public QueuePosition create(@RequestBody QueuePosition queuePosition) {
        return service.createQueuePosition(queuePosition);
    }

    @GetMapping
    public List<QueuePosition> getAll() {
        return service.getAllQueuePositions();
    }

    @GetMapping("/{id}")
    public QueuePosition getById(@PathVariable Long id) {
        return service.getQueuePositionById(id);
    }

    @PutMapping("/{id}")
    public QueuePosition update(@PathVariable Long id,
                                @RequestBody QueuePosition queuePosition) {
        return service.updateQueuePosition(id, queuePosition);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteQueuePosition(id);
    }
}
