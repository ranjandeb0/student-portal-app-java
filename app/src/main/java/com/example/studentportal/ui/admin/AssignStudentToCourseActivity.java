package com.example.studentportal.ui.admin;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentportal.R;
import com.example.studentportal.database.MyDatabaseHelper;

public class AssignStudentToCourseActivity extends AppCompatActivity {

    Spinner spinnerStudent, spinnerCourse;
    Button btnAssign;
    MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_student_to_course);

        db = new MyDatabaseHelper(this);

        spinnerStudent = findViewById(R.id.spinnerStudents);
        spinnerCourse = findViewById(R.id.spinnerCourses);
        btnAssign = findViewById(R.id.btnAssignStudent);

        btnAssign.setOnClickListener(v -> {
            int studentId = (int) spinnerStudent.getSelectedItemId();
            int courseId = (int) spinnerCourse.getSelectedItemId();

            boolean inserted = db.assignStudentToCourse(studentId, courseId);

            Toast.makeText(this,
                    inserted ? "Assigned!" : "Already enrolled",
                    Toast.LENGTH_SHORT).show();
        });
    }
}
