package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.entity.TokenLog;
import com.example.demo.repository.TokenLogRepository;

@RestController
@RequestMapping("/tokenlogs")
public class TokenLogController {

    private final TokenLogRepository repo;

    public TokenLogController(TokenLogRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public TokenLog create(@RequestBody TokenLog log) {
        log.setCreatedAt(LocalDateTime.now());
        return repo.save(log);
    }

    @GetMapping
    public List<TokenLog> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public TokenLog getById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
