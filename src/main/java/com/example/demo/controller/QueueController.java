package com.example.demo.controller;

import com.example.demo.entity.QueuePosition;
import com.example.demo.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queue")
public class QueueController {

    @Autowired
    private QueueService queueService;

    @PostMapping("/add/{tokenId}")
    public QueuePosition add(@PathVariable Long tokenId) {
        return queueService.addToQueue(tokenId);
    }

    @GetMapping("/{tokenId}")
    public QueuePosition get(@PathVariable Long tokenId) {
        return queueService.getQueuePosition(tokenId);
    }
}
