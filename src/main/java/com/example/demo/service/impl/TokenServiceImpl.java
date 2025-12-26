
package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TokenServiceImpl implements TokenService {
    
    private final TokenRepository tokenRepository;
    private final ServiceCounterRepository counterRepository;
    private final TokenLogRepository logRepository;
    private final QueuePositionRepository queueRepository;
    
    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository,
                           ServiceCounterRepository counterRepository,
                           TokenLogRepository logRepository,
                           QueuePositionRepository queueRepository) {
        this.tokenRepository = tokenRepository;
        this.counterRepository = counterRepository;
        this.logRepository = logRepository;
        this.queueRepository = queueRepository;
    }
    
    @Override
    @Transactional
    public Token issueToken(Long serviceCounterId) {
        ServiceCounter counter = counterRepository.findById(serviceCounterId)
                .orElseThrow(() -> new RuntimeException("Service counter not found"));
        
        if (!counter.getIsActive()) {
            throw new IllegalArgumentException("Service counter is not active");
        }
        
        // Get waiting tokens for this counter to determine next number
        List<Token> waitingTokens = tokenRepository
                .findByServiceCounter_IdAndStatusOrderByIssuedAtAsc(serviceCounterId, "WAITING");
        
        int nextNumber = waitingTokens.size() + 1;
        String tokenNumber = counter.getCounterName().charAt(0) + "-" + nextNumber;
        
        Token token = new Token();
        token.setTokenNumber(tokenNumber);
        token.setServiceCounter(counter);
        token.setStatus("WAITING");
        token.setIssuedAt(LocalDateTime.now());
        
        Token savedToken = tokenRepository.save(token);
        
        // Create queue position
        QueuePosition queuePosition = new QueuePosition();
        queuePosition.setToken(savedToken);
        queuePosition.setPosition(nextNumber);
        queueRepository.save(queuePosition);
        
        // Log the issuance
        TokenLog log = new TokenLog();
        log.setToken(savedToken);
        log.setMessage("Token " + tokenNumber + " issued for counter " + counter.getCounterName());
        logRepository.save(log);
        
        return savedToken;
    }
    
    @Override
    @Transactional
    public Token updateStatus(Long tokenId, String newStatus) {
        Token token = tokenRepository.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));
        
        String oldStatus = token.getStatus();
        
        // Validate status transition
        if (!isValidStatusTransition(oldStatus, newStatus)) {
            throw new IllegalArgumentException("Invalid status transition from " + oldStatus + " to " + newStatus);
        }
        
        token.setStatus(newStatus);
        
        if ("COMPLETED".equals(newStatus) || "CANCELLED".equals(newStatus)) {
            token.setCompletedAt(LocalDateTime.now());
        }
        
        Token updatedToken = tokenRepository.save(token);
        
        // Log the status change
        TokenLog log = new TokenLog();
        log.setToken(updatedToken);
        log.setMessage("Status changed from " + oldStatus + " to " + newStatus);
        logRepository.save(log);
        
        return updatedToken;
    }
    
    @Override
    public Token getToken(Long tokenId) {
        return tokenRepository.findById(tokenId)
                .orElseThrow(() -> new RuntimeException("Token not found"));
    }
    
    private boolean isValidStatusTransition(String oldStatus, String newStatus) {
        switch (oldStatus) {
            case "WAITING":
                return "SERVING".equals(newStatus) || "CANCELLED".equals(newStatus);
            case "SERVING":
                return "COMPLETED".equals(newStatus) || "CANCELLED".equals(newStatus);
            case "COMPLETED":
            case "CANCELLED":
                return false; // Terminal states
            default:
                return false;
        }
    }
}
