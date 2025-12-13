package com.example.studentportal.ui.student;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentportal.R;
import com.example.studentportal.database.MyDatabaseHelper;
import com.example.studentportal.ui.student.adapters.StudentCourseAdapter;

public class StudentCoursesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_courses);

        getSupportActionBar().setTitle("My Courses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int studentId = getIntent().getIntExtra("student_id", -1);

        RecyclerView recycler = findViewById(R.id.recyclerStudentCourses);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        recycler.setAdapter(new StudentCourseAdapter(
                db.getCoursesWithResult(studentId)
        ));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
