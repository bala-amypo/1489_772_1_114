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

 
    @PostMapping
    public TokenLog createTokenLog(@RequestBody TokenLog tokenLog) {

        if (tokenLog.getToken() == null || tokenLog.getToken().getId() == null) {
            throw new RuntimeException("Token ID is required");
        }

        Token managedToken = tokenRepository.findById(tokenLog.getToken().getId())
                .orElseThrow(() -> new RuntimeException(
                        "Token not found with id: " + tokenLog.getToken().getId()
                ));

        tokenLog.setToken(managedToken);
        tokenLog.setLoggedAt(LocalDateTime.now());

        return tokenLogRepository.save(tokenLog);
    }

   
    @GetMapping
    public List<TokenLog> getAllTokenLogs() {
        return tokenLogRepository.findAll();
    }

 
    @GetMapping("/{id}")
    public TokenLog getTokenLogById(@PathVariable Long id) {
        return tokenLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TokenLog not found with id: " + id));
    }

 
    @PutMapping("/{id}")
    public TokenLog updateTokenLog(
            @PathVariable Long id,
            @RequestBody TokenLog updatedLog
    ) {
        TokenLog existingLog = tokenLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TokenLog not found with id: " + id));

        existingLog.setLogMessage(updatedLog.getLogMessage());

        return tokenLogRepository.save(existingLog);
    }

    @DeleteMapping("/{id}")
    public String deleteTokenLog(@PathVariable Long id) {

        if (!tokenLogRepository.existsById(id)) {
            throw new RuntimeException("TokenLog not found with id: " + id);
        }

        tokenLogRepository.deleteById(id);
        return "TokenLog deleted successfully with id " + id;
    }
}
