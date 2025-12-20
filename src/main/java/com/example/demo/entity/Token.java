package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String tokenNumber;

    @ManyToOne
    @JoinColumn(name = "service_counter_id", nullable = false)
    private ServiceCounter serviceCounter;

    @Column(nullable = false)
    private String status;   // WAITING / SERVING / COMPLETED / CANCELLED

    @Column(nullable = false)
    private LocalDateTime issuedAt;

    private LocalDateTime completedAt;

    // 🔹 Automatically set issuedAt when token is created
    @PrePersist
    public void prePersistIssuedAt() {
        this.issuedAt = LocalDateTime.now();
    }

    // 🔹 Constructors
    public Token() {}

    // 🔹 Getters & Setters

    public Long getId() {
        return id;
    }

    public String getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(String tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    public ServiceCounter getServiceCounter() {
        return serviceCounter;
    }

    public void setServiceCounter(ServiceCounter serviceCounter) {
        this.serviceCounter = serviceCounter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    // ✅ REQUIRED because service calls token.setIssuedAt(...)
    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}
