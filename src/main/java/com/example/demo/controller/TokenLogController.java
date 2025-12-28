package com.example.demo.controller;

import com.example.demo.entity.TokenLog;
import com.example.demo.service.TokenLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/logs")
public class TokenLogController {

    private final TokenLogService logService;

    public TokenLogController(TokenLogService logService) {
        this.logService = logService;
    }

    @PostMapping("/{tokenId}")
    public TokenLog add(@PathVariable Long tokenId,
                        @RequestBody Map<String, String> req) {
        return logService.addLog(tokenId, req.get("message"));
    }

    @GetMapping("/{tokenId}")
    public List<TokenLog> get(@PathVariable Long tokenId) {
        return logService.getLogs(tokenId);
    }
}
