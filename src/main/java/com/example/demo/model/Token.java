package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer tokenNumber;
    private String status;
    private LocalDateTime createdTime;

    @ManyToOne
    private ServiceCounter counter;

    public Token() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getTokenNumber() { return tokenNumber; }
    public void setTokenNumber(Integer tokenNumber) { this.tokenNumber = tokenNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedTime() { return createdTime; }
    public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }

    public ServiceCounter getCounter() { return counter; }
    public void setCounter(ServiceCounter counter) { this.counter = counter; }
}
