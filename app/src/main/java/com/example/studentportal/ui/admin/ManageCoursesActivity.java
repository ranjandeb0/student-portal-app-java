package com.example.studentportal.ui.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.studentportal.R;
import com.example.studentportal.database.MyDatabaseHelper;
import com.example.studentportal.models.Course;
import com.example.studentportal.ui.admin.adapters.CourseAdapter;

import java.util.List;

public class ManageCoursesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_courses);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Manage Courses");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        db = new MyDatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerCourses);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadCourses();
    }

    private void loadCourses() {
        List<Course> list = db.getAllCourses();

        CourseAdapter adapter = new CourseAdapter(list, new CourseAdapter.CourseActionListener() {
            @Override
            public void onUpdate(Course course) {
                Intent i = new Intent(ManageCoursesActivity.this, UpdateCourseActivity.class);
                i.putExtra("course_id", course.getCourseId());
                startActivity(i);
            }

            @Override
            public void onDelete(Course course) {
                db.deleteCourse(course.getCourseId());
                loadCourses();
            }
        });

        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
