package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Queue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Token token;

    private int position;

    // getters & setters

    public Long getId() {
        return id;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
