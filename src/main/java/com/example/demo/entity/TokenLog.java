package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "token_log")
public class TokenLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "token_id", nullable = false)
    private Token token;

    @NotBlank(message = "Log message is required")
    @Column(nullable = false)
    private String logMessage;

    @Column(nullable = false)
    private LocalDateTime loggedAt;

    // 👇 Swagger input ONLY (not stored in DB)
    @Transient
    @NotNull(message = "Token ID is required")
    private Long tokenId;

    public TokenLog() {
    }

    @PrePersist
    public void setLoggedAt() {
        this.loggedAt = LocalDateTime.now();
    }

    // ---------- GETTERS & SETTERS ----------

    public Long getId() {
        return id;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    public Long getTokenId() {
        return tokenId;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }
}
