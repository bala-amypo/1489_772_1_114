package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    private String status;

    @Column(nullable = false)
    private LocalDateTime issuedAt;

    private LocalDateTime completedAt;

    // 👇 For Swagger input ONLY (not stored in DB)
    @Transient
    @NotNull(message = "Service Counter ID is required")
    private Long serviceCounterId;

    public Token() {
    }

    @PrePersist
    public void prePersist() {
        this.issuedAt = LocalDateTime.now();
        this.status = "WAITING";
        this.tokenNumber = "TKN-" + System.currentTimeMillis();
    }

    // ---------- GETTERS & SETTERS ----------

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

    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public Long getServiceCounterId() {
        return serviceCounterId;
    }

    public void setServiceCounterId(Long serviceCounterId) {
        this.serviceCounterId = serviceCounterId;
    }
}
