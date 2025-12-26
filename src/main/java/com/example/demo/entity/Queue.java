package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "queues")
public class Queue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer currentToken;

    // ✅ getters & setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCurrentToken() {
        return currentToken;
    }

    public void setCurrentToken(Integer currentToken) {
        this.currentToken = currentToken;
    }
}
