package com.example.demo.controller;

import com.example.demo.entity.ServiceCounter;
import com.example.demo.service.ServiceCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/counters")
@CrossOrigin(origins = "*")
public class ServiceCounterController {
    
    @Autowired
    private ServiceCounterService counterService;
    
    @PostMapping
    public ResponseEntity<?> createCounter(@RequestBody ServiceCounter serviceCounter) {
        try {
            if (serviceCounter == null) {
                return ResponseEntity.badRequest().body("Counter data is required");
            }
            
            if (serviceCounter.getCounterName() == null || serviceCounter.getCounterName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Counter name is required");
            }
            
            ServiceCounter created = counterService.addCounter(serviceCounter);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Counter created successfully");
            response.put("counter", created);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create counter");
        }
    }
    
    @GetMapping("/active")
    public ResponseEntity<?> getActiveCounters() {
        try {
            List<ServiceCounter> counters = counterService.getActiveCounters();
            
            Map<String, Object> response = new HashMap<>();
            response.put("counters", counters);
            response.put("count", counters.size());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve active counters");
        }
    }
    
    @GetMapping("/{counterId}")
    public ResponseEntity<?> getCounter(@PathVariable Long counterId) {
        try {
            if (counterId == null || counterId <= 0) {
                return ResponseEntity.badRequest().body("Invalid counterId");
            }
            
            // This would require a new service method
            // For now, return a placeholder response
            Map<String, Object> response = new HashMap<>();
            response.put("counterId", counterId);
            response.put("message", "Endpoint implementation pending");
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve counter");
        }
    }
    
    @PutMapping("/{counterId}/status")
    public ResponseEntity<?> updateCounterStatus(@PathVariable Long counterId,
                                                @RequestBody Map<String, Boolean> request) {
        try {
            if (request == null || !request.containsKey("active")) {
                return ResponseEntity.badRequest().body("active status is required");
            }
            
            // This would require a new service method
            // For now, return a placeholder response
            Map<String, Object> response = new HashMap<>();
            response.put("counterId", counterId);
            response.put("active", request.get("active"));
            response.put("message", "Endpoint implementation pending");
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update counter status");
        }
    }
}