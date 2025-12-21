package com.example.demo.controller;

import com.example.demo.entity.Token;
import com.example.demo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tokens")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    // Create a new token
    @PostMapping
    public ResponseEntity<Token> createToken(@RequestBody Token token) {
        Token savedToken = tokenService.saveToken(token);
        return ResponseEntity.ok(savedToken);
    }

    // Get all tokens
    @GetMapping
    public ResponseEntity<List<Token>> getAllTokens() {
        List<Token> tokens = tokenService.getAllTokens();
        return ResponseEntity.ok(tokens);
    }

    // Get a token by ID
    @GetMapping("/{id}")
    public ResponseEntity<Token> getTokenById(@PathVariable Long id) {
        Token token = tokenService.getTokenById(id);
        return ResponseEntity.ok(token);
    }

    // Update a token
    @PutMapping("/{id}")
    public ResponseEntity<Token> updateToken(@PathVariable Long id, @RequestBody Token token) {
        Token updatedToken = tokenService.updateToken(id, token);
        return ResponseEntity.ok(updatedToken);
    }

    // Delete a token
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteToken(@PathVariable Long id) {
        tokenService.deleteToken(id);
        return ResponseEntity.ok("Token deleted successfully with id: " + id);
    }
}
