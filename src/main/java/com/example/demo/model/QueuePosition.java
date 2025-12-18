package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class QueuePosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tokenId;
    private Integer position;
    private String status;

    // ===== GETTERS =====

    public Long getId() {
        return id;
    }

    public Long getTokenId() {
        return tokenId;
    }

    public Integer getPosition() {
        return position;
    }

    public String getStatus() {
        return status;
    }

    // ===== SETTERS =====

    public void setId(Long id) {
        this.id = id;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
