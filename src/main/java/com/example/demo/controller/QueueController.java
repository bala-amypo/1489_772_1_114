package com.example.demo.controller;

import com.example.demo.entity.QueuePosition;
import com.example.demo.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/queue")
@CrossOrigin(origins = "*")
public class QueueController {
    
    @Autowired
    private QueueService queueService;
    
    @PutMapping("/{tokenId}/position")
    public ResponseEntity<?> updatePosition(@PathVariable Long tokenId,
                                           @RequestBody Map<String, Integer> request) {
        try {
            if (request == null || !request.containsKey("position")) {
                return ResponseEntity.badRequest().body("position is required");
            }
            
            Integer position = request.get("position");
            
            if (position == null || position < 1) {
                return ResponseEntity.badRequest().body("Position must be at least 1");
            }
            
            QueuePosition queuePosition = queueService.updateQueuePosition(tokenId, position);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Queue position updated successfully");
            response.put("queuePosition", queuePosition);
            
            return ResponseEntity.ok(response);
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update queue position");
        }
    }
    
    @GetMapping("/{tokenId}/position")
    public ResponseEntity<?> getPosition(@PathVariable Long tokenId) {
        try {
            if (tokenId == null || tokenId <= 0) {
                return ResponseEntity.badRequest().body("Invalid tokenId");
            }
            
            QueuePosition queuePosition = queueService.getPosition(tokenId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("queuePosition", queuePosition);
            
            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve queue position");
        }
    }
    
    @GetMapping("/counter/{counterId}")
    public ResponseEntity<?> getQueueForCounter(@PathVariable Long counterId) {
        try {
            if (counterId == null || counterId <= 0) {
                return ResponseEntity.badRequest().body("Invalid counterId");
            }
            
            // This would require a new service method
            // For now, return a placeholder response
            Map<String, Object> response = new HashMap<>();
            response.put("counterId", counterId);
            response.put("message", "Endpoint implementation pending");
            response.put("waitingTokens", 0);
            response.put("currentToken", null);
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve queue for counter");
        }
    }
    
    @GetMapping("/dashboard")
    public ResponseEntity<?> getQueueDashboard() {
        try {
            // This endpoint would provide an overview of all queues
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Queue dashboard endpoint");
            response.put("activeCounters", 0);
            response.put("totalWaiting", 0);
            response.put("totalServing", 0);
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve queue dashboard");
        }
    }
}