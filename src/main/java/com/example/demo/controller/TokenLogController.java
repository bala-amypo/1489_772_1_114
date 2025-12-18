package com.example.demo.controller;

import com.example.demo.entity.Token;
import com.example.demo.model.TokenLog;
import com.example.demo.repository.TokenLogRepository;
import com.example.demo.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/token-logs")
public class TokenLogController {

    @Autowired
    private TokenLogRepository tokenLogRepository;

    @Autowired
    private TokenRepository tokenRepository;

    // POST
    @PostMapping
    public TokenLog createTokenLog(@RequestBody TokenLog tokenLog) {
        Token managedToken = tokenRepository.findById(tokenLog.getToken().getId())
                .orElseThrow(() -> new RuntimeException("Token not found"));
        tokenLog.setToken(managedToken);
        tokenLog.setLoggedAt(LocalDateTime.now());
        return tokenLogRepository.save(tokenLog);
    }

    // GET all
    @GetMapping
    public List<TokenLog> getAllTokenLogs() {
        return tokenLogRepository.findAll();
    }

    // GET by id
    @GetMapping("/{id}")
    public TokenLog getTokenLog(@PathVariable Long id) {
        return tokenLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TokenLog not found"));
    }

    // PUT / update
    @PutMapping("/{id}")
    public TokenLog updateTokenLog(@PathVariable Long id, @RequestBody TokenLog updatedLog) {
        TokenLog log = tokenLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TokenLog not found"));
        log.setLogMessage(updatedLog.getLogMessage());
        return tokenLogRepository.save(log);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteTokenLog(@PathVariable Long id) {
        tokenLogRepository.deleteById(id);
        return "TokenLog deleted successfully with id " + id;
    }
}
