package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ServiceCounter;
import com.example.demo.entity.Token;
import com.example.demo.repository.ServiceCounterRepository;
import com.example.demo.repository.TokenRepository;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepo;
    private final ServiceCounterRepository counterRepo;

    public TokenServiceImpl(TokenRepository tokenRepo,
                            ServiceCounterRepository counterRepo) {
        this.tokenRepo = tokenRepo;
        this.counterRepo = counterRepo;
    }

    @Override
    public Token generateToken(Long counterId) {

        ServiceCounter counter = counterRepo.findById(counterId)
                .orElseThrow(() -> new RuntimeException("Counter not found"));

        if (!counter.isActive()) {
            throw new RuntimeException("Counter not active");
        }

        Token token = new Token();
        token.setTokenNumber("TKN-" + System.currentTimeMillis());
        token.setStatus("ISSUED");
        token.setIssuedAt(LocalDateTime.now());
        token.setServiceCounter(counter);

        return tokenRepo.save(token);
    }

    @Override
    public List<Token> getAllTokens() {
        return tokenRepo.findAll();
    }
}
