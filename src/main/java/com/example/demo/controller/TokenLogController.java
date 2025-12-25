package com.example.demo.controller;

import com.example.demo.entity.TokenLog;
import com.example.demo.service.TokenLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
@Tag(name = "Token Logs")
public class TokenLogController {

    private final TokenLogService service;

    public TokenLogController(TokenLogService service) {
        this.service = service;
    }

    @PostMapping("/{tokenId}")
    @Operation(summary = "Add token log")
    public TokenLog add(@PathVariable Long tokenId,
                        @RequestBody String message) {
        return service.addLog(tokenId, message);
    }

    @GetMapping("/{tokenId}")
    @Operation(summary = "Get token logs")
    public List<TokenLog> get(@PathVariable Long tokenId) {
        return service.getLogs(tokenId);
    }
}
