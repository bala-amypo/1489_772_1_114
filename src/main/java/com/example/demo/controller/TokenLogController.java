package com.example.demo.controller;

import com.example.demo.entity.TokenLog;
import com.example.demo.service.TokenLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tokenLogs")
public class TokenLogController {

    @Autowired
    private TokenLogService tokenLogService;

    // Create a log
    @PostMapping
    public TokenLog createTokenLog(@RequestBody TokenLog tokenLog) {
        return tokenLogService.saveTokenLog(tokenLog);
    }

    // Get all logs
    @GetMapping
    public List<TokenLog> getAllTokenLogs() {
        return tokenLogService.getAllTokenLogs();
    }

    // Get log by id
    @GetMapping("/{id}")
    public TokenLog getTokenLogById(@PathVariable Long id) {
        return tokenLogService.getTokenLogById(id);
    }

    // Delete log
    @DeleteMapping("/{id}")
    public void deleteTokenLog(@PathVariable Long id) {
        tokenLogService.deleteTokenLog(id);
    }

    // Get logs by token id
    @GetMapping("/token/{tokenId}")
    public List<TokenLog> getLogsByTokenId(@PathVariable Long tokenId) {
        return tokenLogService.getLogsByTokenId(tokenId);
    }
}
