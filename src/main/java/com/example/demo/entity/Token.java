package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank
    private String tokenNumber;

    @ManyToOne
    @JoinColumn(name = "service_counter_id")
    private ServiceCounter serviceCounter;

    @NotBlank
    private String status; // WAITING / SERVING / COMPLETED / CANCELLED

    private LocalDateTime issuedAt;

    private LocalDateTime completedAt;

    @PrePersist
    public void prePersist() {
        issuedAt = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTokenNumber() { return tokenNumber; }
    public void setTokenNumber(String tokenNumber) { this.tokenNumber = tokenNumber; }
    public ServiceCounter getServiceCounter() { return serviceCounter; }
    public void setServiceCounter(ServiceCounter serviceCounter) { this.serviceCounter = serviceCounter; }
    public String getStatus() { return status; }
    public void setStatus(String status) { 
        if(this.status != null && this.status.equals("COMPLETED") && !status.equals("COMPLETED")){
            throw new RuntimeException("Cannot change status once COMPLETED");
        }
        this.status = status; 
        if(status.equals("COMPLETED")){
            completedAt = LocalDateTime.now();
        }
    }
    public LocalDateTime getIssuedAt() { return issuedAt; }
    public LocalDateTime getCompletedAt() { return completedAt; }
}
