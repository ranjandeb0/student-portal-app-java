package com.example.studentportal.ui.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentportal.R;
import com.example.studentportal.ui.LoginActivity;

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
