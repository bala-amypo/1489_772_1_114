package com.example.demo.controller;

import com.example.demo.model.TokenLog;
import com.example.demo.service.TokenLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/token-logs")
public class TokenLogController {

    private final TokenLogService tokenLogService;

    public TokenLogController(TokenLogService tokenLogService) {
        this.tokenLogService = tokenLogService;
    }

    // Create
    @PostMapping
    public TokenLog createLog(@RequestBody TokenLog tokenLog) {
        return tokenLogService.createLog(tokenLog);
    }

    // Read All
    @GetMapping
    public List<TokenLog> getAllLogs() {
        return tokenLogService.getAllLogs();
    }

    // Read by Id
    @GetMapping("/{id}")
    public ResponseEntity<TokenLog> getLogById(@PathVariable Long id) {
        return tokenLogService.getLogById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<TokenLog> updateLog(@PathVariable Long id, @RequestBody TokenLog tokenLog) {
        return tokenLogService.getLogById(id)
                .map(existingLog -> {
                    existingLog.setLogMessage(tokenLog.getLogMessage());
                    existingLog.setToken(tokenLog.getToken());
                    return ResponseEntity.ok(tokenLogService.updateLog(existingLog));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLog(@PathVariable Long id) {
        tokenLogService.deleteLog(id);
        return ResponseEntity.noContent().build();
    }
}
