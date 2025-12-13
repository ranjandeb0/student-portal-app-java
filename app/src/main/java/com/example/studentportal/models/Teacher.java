package com.example.studentportal.models;


public class Teacher extends User {

    private String username;

    public Teacher(int id, String name, String email, String phone,
                   String username, String password) {

        super(id, name, email, phone, password);
        this.username = username;
    }


    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    @Override
    public UserRole getRole() {
        return UserRole.TEACHER;
    }
}

