package com.example.studentportal.models;

public class Course {

    private int courseId;
    private String title;
    private String courseCode;
    private double credit;    // REAL value
    private int teacherId;

    public Course(int courseId, String title, String courseCode,
                  double credit, int teacherId) {

        this.courseId = courseId;
        this.title = title;
        this.courseCode = courseCode;
        this.credit = credit;
        this.teacherId = teacherId;
    }

    // Getters
    public int getCourseId() { return courseId; }
    public String getTitle() { return title; }
    public String getCourseCode() { return courseCode; }
    public double getCredit() { return credit; }
    public int getTeacherId() { return teacherId; }

    // Setters
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}
