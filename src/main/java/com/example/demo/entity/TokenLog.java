
package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "token_log")
public class TokenLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "message", columnDefinition = "TEXT")
    private String message;
    
    @Column(name = "logged_at")
    private LocalDateTime loggedAt = LocalDateTime.now();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "token_id")
    private Token token;
    
    public TokenLog() {}
    
    public TokenLog(String message, Token token) {
        this.message = message;
        this.token = token;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public LocalDateTime getLoggedAt() { return loggedAt; }
    public void setLoggedAt(LocalDateTime loggedAt) { this.loggedAt = loggedAt; }
    
    public Token getToken() { return token; }
    public void setToken(Token token) { this.token = token; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenLog tokenLog = (TokenLog) o;
        return Objects.equals(id, tokenLog.id) && 
               Objects.equals(loggedAt, tokenLog.loggedAt);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, loggedAt);
    }
    
    @Override
    public String toString() {
        return "TokenLog{id=" + id + ", message='" + message + 
               "', loggedAt=" + loggedAt + "}";
    }
}
