package com.example.studentportal.models;

public class Admin extends User {

    private String username;

    public Admin(String username, String password) {
        super(-1, "Admin", "admin@system.com", "N/A", password);
        this.username = username;
    }

    public String getUsername() { return username; }

    @Override
    public UserRole getRole() {
        return UserRole.ADMIN;
    }
}