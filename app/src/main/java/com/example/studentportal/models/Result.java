package com.example.studentportal.models;

public class Result {
    private int studentId;
    private int courseId;
    private double marks;
    private String grade;

    public Result(int studentId, int courseId, double marks, String grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.marks = marks;
        this.grade = grade;
    }

    public int getStudentId() { return studentId; }
    public int getCourseId() { return courseId; }
    public double getMarks() { return marks; }
    public String getGrade() { return grade; }
}
