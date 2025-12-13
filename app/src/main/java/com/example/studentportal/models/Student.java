package com.example.studentportal.models;

public class Student extends User {

    private String regId; // login ID

    public Student(int id, String name, String email, String phone,
                   String password, String regId) {

        super(id, name, email, phone, password);
        this.regId = regId;
    }

    public String getRegId() { return regId; }
    public void setRegId(String regId) { this.regId = regId; }

    @Override
    public UserRole getRole() {
        return UserRole.STUDENT;
    }
}
