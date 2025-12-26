package com.example.demo.controller;

import com.example.demo.entity.Token;
import com.example.demo.service.impl.TokenServiceImpl;
import com.example.demo.service.impl.QueueServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class TokenController {

    private final TokenServiceImpl tokenService;
    private final QueueServiceImpl queueService;

    @Autowired
    public TokenController(TokenServiceImpl tokenService, QueueServiceImpl queueService) {
        this.tokenService = tokenService;
        this.queueService = queueService;
    }

    @PostMapping("/issue/{counterId}")
    public ResponseEntity<Token> issueToken(@PathVariable Long counterId) {
        Token t = tokenService.issueToken(counterId);
        return ResponseEntity.ok(t);
    }

    @PostMapping("/updateStatus/{tokenId}")
    public ResponseEntity<Token> updateStatus(@PathVariable Long tokenId, @RequestBody StatusRequest req) {
        Token t = tokenService.updateStatus(tokenId, req.getStatus());
        return ResponseEntity.ok(t);
    }

    @PostMapping("/updateQueue/{tokenId}")
    public ResponseEntity<?> updateQueue(@PathVariable Long tokenId, @RequestBody QueueRequest req) {
        return ResponseEntity.ok(queueService.updateQueuePosition(tokenId, req.getPosition()));
    }

    // DTOs
    @Data
    public static class StatusRequest {
        private String status;
    }

    @Data
    public static class QueueRequest {
        private Integer position;
    }
}
