package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.QueuePosition;
import com.example.demo.service.QueuePositionService;

@RestController
@RequestMapping("/queue")
public class QueuePositionController {

    @Autowired
    private QueuePositionService service;

    @PostMapping
    public QueuePosition create(@RequestBody QueuePosition qp) {
        return service.saveQueuePosition(qp);
    }

    @GetMapping
    public List<QueuePosition> getAll() {
        return service.getAllQueuePositions();
    }

    @GetMapping("/{id}")
    public QueuePosition getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/token/{tokenId}")
    public QueuePosition getByTokenId(@PathVariable Long tokenId) {
        return service.getByTokenId(tokenId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
