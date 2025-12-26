package com.example.demo.controller;

import com.example.demo.entity.Token;
import com.example.demo.entity.TokenLog;
import com.example.demo.entity.QueuePosition;
import com.example.demo.service.impl.TokenServiceImpl;
import com.example.demo.service.impl.QueueServiceImpl;
import com.example.demo.service.impl.TokenLogServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/token")
public class TokenController {

    private final TokenServiceImpl tokenService;
    private final QueueServiceImpl queueService;
    private final TokenLogServiceImpl logService;

    public TokenController(TokenServiceImpl tokenService, QueueServiceImpl queueService, TokenLogServiceImpl logService) {
        this.tokenService = tokenService;
        this.queueService = queueService;
        this.logService = logService;
    }

    @PostMapping("/issue/{counterId}")
    public ResponseEntity<Token> issueToken(@PathVariable Long counterId) {
        Token token = tokenService.issueToken(counterId);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/status/{tokenId}")
    public ResponseEntity<Token> updateStatus(@PathVariable Long tokenId, @RequestParam String status) {
        Token token = tokenService.updateStatus(tokenId, status);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/{tokenId}")
    public ResponseEntity<Token> getToken(@PathVariable Long tokenId) {
        Token token = tokenService.getToken(tokenId);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/queue/{tokenId}")
    public ResponseEntity<QueuePosition> updateQueue(@PathVariable Long tokenId, @RequestParam Integer position) {
        QueuePosition qp = queueService.updateQueuePosition(tokenId, position);
        return ResponseEntity.ok(qp);
    }

    @GetMapping("/queue/{tokenId}")
    public ResponseEntity<QueuePosition> getQueuePosition(@PathVariable Long tokenId) {
        QueuePosition qp = queueService.getPosition(tokenId);
        return ResponseEntity.ok(qp);
    }

    @PostMapping("/log/{tokenId}")
    public ResponseEntity<TokenLog> addLog(@PathVariable Long tokenId, @RequestParam String message) {
        TokenLog log = logService.addLog(tokenId, message);
        return ResponseEntity.ok(log);
    }

    @GetMapping("/log/{tokenId}")
    public ResponseEntity<List<TokenLog>> getLogs(@PathVariable Long tokenId) {
        List<TokenLog> logs = logService.getLogs(tokenId);
        return ResponseEntity.ok(logs);
    }
}
