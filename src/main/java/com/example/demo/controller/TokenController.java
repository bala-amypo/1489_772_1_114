package com.example.demo.controller;

import com.example.demo.entity.Token;
import com.example.demo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/tokens")
@CrossOrigin(origins = "*")
public class TokenController {
    
    @Autowired
    private TokenService tokenService;
    
    @PostMapping("/issue")
    public ResponseEntity<?> issueToken(@RequestBody Map<String, Long> request) {
        try {
            if (request == null || !request.containsKey("counterId")) {
                return ResponseEntity.badRequest().body("counterId is required");
            }
            
            Long counterId = request.get("counterId");
            
            if (counterId == null || counterId <= 0) {
                return ResponseEntity.badRequest().body("Invalid counterId");
            }
            
            Token token = tokenService.issueToken(counterId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Token issued successfully");
            response.put("token", token);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to issue token");
        }
    }
    
    @PutMapping("/{tokenId}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long tokenId, 
                                         @RequestBody Map<String, String> request) {
        try {
            if (request == null || !request.containsKey("status")) {
                return ResponseEntity.badRequest().body("status is required");
            }
            
            String status = request.get("status");
            
            if (status == null || status.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Status cannot be empty");
            }
            
            // Validate status value
            if (!isValidStatus(status)) {
                return ResponseEntity.badRequest().body("Invalid status value. Must be one of: WAITING, SERVING, COMPLETED, CANCELLED");
            }
            
            Token token = tokenService.updateStatus(tokenId, status);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Token status updated successfully");
            response.put("token", token);
            
            return ResponseEntity.ok(response);
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update token status");
        }
    }
    
    @GetMapping("/{tokenId}")
    public ResponseEntity<?> getToken(@PathVariable Long tokenId) {
        try {
            if (tokenId == null || tokenId <= 0) {
                return ResponseEntity.badRequest().body("Invalid tokenId");
            }
            
            Token token = tokenService.getToken(tokenId);
            return ResponseEntity.ok(token);
            
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve token");
        }
    }
    
    @GetMapping("/counter/{counterId}")
    public ResponseEntity<?> getTokensByCounter(@PathVariable Long counterId,
                                               @RequestParam(required = false) String status) {
        try {
            // This endpoint would require a new service method
            // For now, return a placeholder response
            Map<String, Object> response = new HashMap<>();
            response.put("counterId", counterId);
            response.put("status", status);
            response.put("message", "Endpoint implementation pending");
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve tokens");
        }
    }
    
    @GetMapping("/number/{tokenNumber}")
    public ResponseEntity<?> getTokenByNumber(@PathVariable String tokenNumber) {
        try {
            if (tokenNumber == null || tokenNumber.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("tokenNumber is required");
            }
            
            // This would require a new service method
            // For now, return a placeholder response
            Map<String, Object> response = new HashMap<>();
            response.put("tokenNumber", tokenNumber);
            response.put("message", "Endpoint implementation pending");
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve token");
        }
    }
    
    private boolean isValidStatus(String status) {
        return status != null && 
               (status.equals("WAITING") || 
                status.equals("SERVING") || 
                status.equals("COMPLETED") || 
                status.equals("CANCELLED"));
    }
}