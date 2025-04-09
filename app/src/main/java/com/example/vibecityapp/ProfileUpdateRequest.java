package com.example.vibecityapp;

import java.util.List;

public class ProfileUpdateRequest {
    private String username;
    private List<String> interests;

    public ProfileUpdateRequest(String username, List<String> interests) {
        this.username = username;
        this.interests = interests;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }
}