package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "token_number", unique = true)
    private String tokenNumber;
    
    private String status = "WAITING"; // WAITING, SERVING, COMPLETED, CANCELLED
    
    @Column(name = "issued_at")
    private LocalDateTime issuedAt = LocalDateTime.now();
    
    @Column(name = "completed_at")
    private LocalDateTime completedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_counter_id")
    private ServiceCounter serviceCounter;
    
    @OneToOne(mappedBy = "token", cascade = CascadeType.ALL)
    private QueuePosition queuePosition;
    
    @OneToMany(mappedBy = "token", cascade = CascadeType.ALL)
    private List<TokenLog> logs = new ArrayList<>();
    
    public Token() {}
    
    public Token(String tokenNumber, ServiceCounter serviceCounter) {
        this.tokenNumber = tokenNumber;
        this.serviceCounter = serviceCounter;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTokenNumber() { return tokenNumber; }
    public void setTokenNumber(String tokenNumber) { this.tokenNumber = tokenNumber; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getIssuedAt() { return issuedAt; }
    public void setIssuedAt(LocalDateTime issuedAt) { this.issuedAt = issuedAt; }
    
    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
    
    public ServiceCounter getServiceCounter() { return serviceCounter; }
    public void setServiceCounter(ServiceCounter serviceCounter) { this.serviceCounter = serviceCounter; }
    
    public QueuePosition getQueuePosition() { return queuePosition; }
    public void setQueuePosition(QueuePosition queuePosition) { this.queuePosition = queuePosition; }
    
    public List<TokenLog> getLogs() { return logs; }
    public void setLogs(List<TokenLog> logs) { this.logs = logs; }
}