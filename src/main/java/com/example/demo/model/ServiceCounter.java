package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class ServiceCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String counterName;
    private boolean active;

    public ServiceCounter() {}

    public ServiceCounter(Long id, String counterName, boolean active) {
        this.id = id;
        this.counterName = counterName;
        this.active = active;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCounterName() { return counterName; }
    public void setCounterName(String counterName) { this.counterName = counterName; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
