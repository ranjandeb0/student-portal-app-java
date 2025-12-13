package com.example.studentportal.ui.admin;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentportal.R;
import com.example.studentportal.database.MyDatabaseHelper;
import com.example.studentportal.models.Course;
import com.example.studentportal.ui.admin.adapters.CourseMultiSelectAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SelectCoursesForStudentActivity extends AppCompatActivity {

    RecyclerView recycler;
    FloatingActionButton fab;
    MyDatabaseHelper db;
    CourseMultiSelectAdapter adapter;
    int studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_courses);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Select Courses");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        studentId = getIntent().getIntExtra("student_id", -1);
        if (studentId == -1) {
            Toast.makeText(this, "Invalid student", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        recycler = findViewById(R.id.recyclerCourses);
        fab = findViewById(R.id.fabSave);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        db = new MyDatabaseHelper(this);

        List<Course> list = db.getUnassignedCoursesForStudent(studentId);
        adapter = new CourseMultiSelectAdapter(list, selectedIds -> {
            // optional: handle selection changed if you want
        });
        recycler.setAdapter(adapter);

        fab.setOnClickListener(v -> {
            Set<Integer> sel = adapter.getSelectedIds();
            if (sel.isEmpty()) {
                Toast.makeText(this, "Please select at least one course", Toast.LENGTH_SHORT).show();
                return;
            }
            List<Integer> ids = new ArrayList<>(sel);
            boolean ok = db.assignStudentToCourses(studentId, ids);
            Toast.makeText(this, ok ? "Assigned successfully" : "Assignment failed", Toast.LENGTH_SHORT).show();
            if (ok) finish();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
