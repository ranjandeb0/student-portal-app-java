package com.example.studentportal.ui.admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentportal.R;
import com.example.studentportal.ui.LoginActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class AdminDashboardActivity extends AppCompatActivity {

    View cardManageStudents, cardManageTeachers, cardManageCourses, cardAssignStudents, cardAssignTeachers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        setContentView(R.layout.activity_admin_dashboard);

        initViews();
        initListeners();
    }

    private void initViews() {
        cardManageStudents = findViewById(R.id.cardManageStudents);
        cardManageTeachers = findViewById(R.id.cardManageTeachers);
        cardManageCourses = findViewById(R.id.cardManageCourses);
        cardAssignStudents = findViewById(R.id.cardAssignStudents);
        cardAssignTeachers = findViewById(R.id.cardAssignTeachers);
    }

    private void initListeners() {

        findViewById(R.id.cardManageStudents).setOnClickListener(v ->
                startActivity(new Intent(this, ManageStudentsActivity.class))
        );

        findViewById(R.id.cardAddStudent).setOnClickListener(v ->
                startActivity(new Intent(this, AddStudentActivity.class))
        );

        findViewById(R.id.cardManageTeachers).setOnClickListener(v ->
                startActivity(new Intent(this, ManageTeachersActivity.class))
        );

        findViewById(R.id.cardAddTeacher).setOnClickListener(v ->
                startActivity(new Intent(this, AddTeacherActivity.class))
        );

        findViewById(R.id.cardManageCourses).setOnClickListener(v ->
                startActivity(new Intent(this, ManageCoursesActivity.class))
        );

        findViewById(R.id.cardAddCourse).setOnClickListener(v ->
                startActivity(new Intent(this, AddCourseActivity.class))
        );

        cardAssignStudents.setOnClickListener(v ->
                startActivity(new Intent(this, SelectStudentActivity.class)));

        cardAssignTeachers.setOnClickListener(v ->
                startActivity(new Intent(this, SelectTeacherActivity.class)));
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
