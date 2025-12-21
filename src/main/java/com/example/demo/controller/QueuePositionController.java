package com.example.demo.controller;

import java.util.List;

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
    public QueuePosition create(@RequestBody QueuePosition queuePosition) {
        return service.create(queuePosition);
    }

    @GetMapping
    public List<QueuePosition> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public QueuePosition getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public QueuePosition update(@PathVariable Long id,
                                @RequestBody QueuePosition queuePosition) {
        return service.update(id, queuePosition);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
