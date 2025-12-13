package com.example.studentportal.ui.teacher;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentportal.R;
import com.example.studentportal.database.MyDatabaseHelper;
import com.example.studentportal.models.Course;
import com.example.studentportal.ui.teacher.adapters.TeacherCourseAdapter;

import java.util.List;

public class TeacherCoursesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_courses);

        getSupportActionBar().setTitle("My Courses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int teacherId = getIntent().getIntExtra("teacher_id", -1);

        RecyclerView recycler = findViewById(R.id.recyclerCourses);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        List<Course> courses = db.getCoursesByTeacher(teacherId);

        recycler.setAdapter(new TeacherCourseAdapter(courses, course -> {
            Intent i = new Intent(this, SelectStudentForResultActivity.class);
            i.putExtra("course_id", course.getCourseId());
            startActivity(i);
        }));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
