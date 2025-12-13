package com.example.studentportal.ui.student;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentportal.R;

public class StudentDashboardActivity extends AppCompatActivity {

    int studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        getSupportActionBar().setTitle("Student Dashboard");

        studentId = getIntent().getIntExtra("student_id", -1);

        findViewById(R.id.btnProfile).setOnClickListener(v ->
                startActivity(new Intent(this, StudentProfileActivity.class)
                        .putExtra("student_id", studentId)));

        findViewById(R.id.btnCourses).setOnClickListener(v ->
                startActivity(new Intent(this, StudentCoursesActivity.class)
                        .putExtra("student_id", studentId)));
    }
}
