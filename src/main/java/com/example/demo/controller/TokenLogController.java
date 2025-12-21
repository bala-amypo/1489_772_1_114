package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.TokenLog;
import com.example.demo.service.TokenLogService;
import java.util.List;

@RestController
@RequestMapping("/logs")
public class TokenLogController {

    private final TokenLogService logService;

    @Autowired
    public TokenLogController(TokenLogService logService) {
        this.logService = logService;
    }

    @PostMapping
    public TokenLog createLog(@RequestBody TokenLog log) {
        return logService.createLog(log);
    }

    @GetMapping("/{id}")
    public TokenLog getLog(@PathVariable Long id) {
        return logService.getLog(id);
    }

    @GetMapping
    public List<TokenLog> getAllLogs() {
        return logService.getAllLogs();
    }

    @GetMapping("/token/{tokenId}")
    public List<TokenLog> getLogsByToken(@PathVariable Long tokenId) {
        return logService.getLogsByToken(tokenId);
    }

    @DeleteMapping("/{id}")
    public void deleteLog(@PathVariable Long id) {
        logService.deleteLog(id);
    }
}
