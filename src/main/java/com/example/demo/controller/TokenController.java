package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.entity.Token;
import com.example.demo.repository.TokenRepository;

@RestController
@RequestMapping("/tokens")
public class TokenController {

    private final TokenRepository repo;

    public TokenController(TokenRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Token create(@RequestBody Token token) {
        token.setIssuedAt(LocalDateTime.now());
        return repo.save(token);
    }

    @GetMapping
    public List<Token> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Token getById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Token update(@PathVariable Long id, @RequestBody Token token) {
        Token t = repo.findById(id).orElse(null);
        if (t == null) return null;

        t.setTokenNumber(token.getTokenNumber());
        t.setServiceCounterId(token.getServiceCounterId());
        t.setStatus(token.getStatus());
        t.setCompletedAt(token.getCompletedAt());

        return repo.save(t);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
