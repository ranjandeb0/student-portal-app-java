package com.example.studentportal.ui.teacher;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentportal.R;

public class TeacherDashboardActivity extends AppCompatActivity {

    int teacherId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        getSupportActionBar().setTitle("Teacher Dashboard");

        teacherId = getIntent().getIntExtra("teacher_id", -1);

        findViewById(R.id.cardProfile).setOnClickListener(v ->
                startActivity(new Intent(this, TeacherProfileActivity.class)
                        .putExtra("teacher_id", teacherId)));

        findViewById(R.id.cardCourses).setOnClickListener(v ->
                startActivity(new Intent(this, TeacherCoursesActivity.class)
                        .putExtra("teacher_id", teacherId)));
    }
}
