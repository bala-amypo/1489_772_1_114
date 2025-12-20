package com.example.demo.entity;

import jakarta.persistence.*;
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

    // Used ONLY for input (Swagger / JSON)
    @Transient
    private Long tokenId;

    @Column(nullable = false)
    private String logMessage;

    @Column(nullable = false)
    private LocalDateTime loggedAt;

    @PrePersist
    public void setLoggedAt() {
        this.loggedAt = LocalDateTime.now();
    }

    // ---------------- GETTERS & SETTERS ----------------

    public Long getId() {
        return id;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Long getTokenId() {
        return tokenId;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
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
}
