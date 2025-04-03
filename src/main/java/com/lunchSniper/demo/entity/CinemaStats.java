package com.lunchSniper.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CinemaStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String webConnection;
    private int tps;

    public CinemaStats(Object o, String 롯데시네마_서울, String s, int i) {
    }

    // Getters and Setters
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

    public String getWebConnection() {
        return webConnection;
    }

    public void setWebConnection(String webConnection) {
        this.webConnection = webConnection;
    }

    public int getTps() {
        return tps;
    }

    public void setTps(int tps) {
        this.tps = tps;
    }
}
