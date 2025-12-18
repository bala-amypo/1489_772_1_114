package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(
    name = "token",
    uniqueConstraints = @UniqueConstraint(columnNames = "tokenNumber")
)
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String tokenNumber;

    @ManyToOne
    @JoinColumn(name = "service_counter_id", nullable = false)
    private ServiceCounter serviceCounter;

    private String status;

    private LocalDateTime issuedAt;

    private LocalDateTime completedAt;

    public Token() {}

    @PrePersist
    public void onCreate() {
        this.issuedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = "WAITING";
        }
    }

    // Getters & Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTokenNumber() { return tokenNumber; }

    public void setTokenNumber(String tokenNumber) { this.tokenNumber = tokenNumber; }

    public ServiceCounter getServiceCounter() { return serviceCounter; }

    public void setServiceCounter(ServiceCounter serviceCounter) {
        this.serviceCounter = serviceCounter;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;

        if ("COMPLETED".equals(status)) {
            this.completedAt = LocalDateTime.now();
        }
    }

    public LocalDateTime getIssuedAt() { return issuedAt; }

    public LocalDateTime getCompletedAt() { return completedAt; }
}
