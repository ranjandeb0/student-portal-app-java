package com.example.studentportal.ui.admin;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentportal.R;
import com.example.studentportal.database.MyDatabaseHelper;

public class AssignTeacherToCourseActivity extends AppCompatActivity {

    Spinner spinnerTeacher, spinnerCourse;
    Button btnAssign;
    MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_teacher_to_course);

        db = new MyDatabaseHelper(this);

        spinnerTeacher = findViewById(R.id.spinnerTeachers);
        spinnerCourse = findViewById(R.id.spinnerCoursesAssign);
        btnAssign = findViewById(R.id.btnAssignTeacher);

        btnAssign.setOnClickListener(v -> {
            int teacherId = (int) spinnerTeacher.getSelectedItemId();
            int courseId = (int) spinnerCourse.getSelectedItemId();

            boolean updated = db.assignTeacherToCourse(courseId, teacherId);

            Toast.makeText(this,
                    updated ? "Teacher Assigned!" : "Failed",
                    Toast.LENGTH_SHORT).show();
        });
    }
}
