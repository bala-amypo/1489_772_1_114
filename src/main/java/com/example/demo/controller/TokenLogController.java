package com.example.demo.controller;

import com.example.demo.entity.TokenLog;
import com.example.demo.service.TokenLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/logs")
@CrossOrigin(origins = "*")
public class TokenLogController {
    
    @Autowired
    private TokenLogService logService;
    
    @PostMapping("/{tokenId}")
    public ResponseEntity<?> addLog(@PathVariable Long tokenId,
                                   @RequestBody Map<String, String> request) {
        try {
            if (request == null || !request.containsKey("message")) {
                return ResponseEntity.badRequest().body("message is required");
            }
            
            String message = request.get("message");
            
            if (message == null || message.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Message cannot be empty");
            }
            
            TokenLog log = logService.addLog(tokenId, message);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Log added successfully");
            response.put("log", log);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add log");
        }
    }
    
    @GetMapping("/{tokenId}")
    public ResponseEntity<?> getLogs(@PathVariable Long tokenId) {
        try {
            if (tokenId == null || tokenId <= 0) {
                return ResponseEntity.badRequest().body("Invalid tokenId");
            }
            
            List<TokenLog> logs = logService.getLogs(tokenId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("tokenId", tokenId);
            response.put("logs", logs);
            response.put("count", logs.size());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve logs");
        }
    }
    
    @GetMapping("/recent")
    public ResponseEntity<?> getRecentLogs(@RequestParam(defaultValue = "10") int limit) {
        try {
            if (limit <= 0 || limit > 100) {
                return ResponseEntity.badRequest().body("Limit must be between 1 and 100");
            }
            
            // This would require a new service method
            // For now, return a placeholder response
            Map<String, Object> response = new HashMap<>();
            response.put("limit", limit);
            response.put("message", "Endpoint implementation pending");
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve recent logs");
        }
    }
}