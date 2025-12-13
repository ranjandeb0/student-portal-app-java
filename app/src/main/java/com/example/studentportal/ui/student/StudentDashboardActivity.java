package com.example.studentportal.ui.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentportal.R;
import com.example.studentportal.ui.LoginActivity;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish(); // Prevents returning to dashboard

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
