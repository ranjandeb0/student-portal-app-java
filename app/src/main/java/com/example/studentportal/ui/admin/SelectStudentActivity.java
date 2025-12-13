package com.example.studentportal.ui.admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentportal.R;
import com.example.studentportal.database.MyDatabaseHelper;
import com.example.studentportal.models.Student;
import com.example.studentportal.ui.admin.adapters.StudentListAdapter;

import java.util.List;

public class SelectStudentActivity extends AppCompatActivity {

    RecyclerView recycler;
    MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_student);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Select Student");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recycler = findViewById(R.id.recyclerStudents);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        db = new MyDatabaseHelper(this);

        List<Student> list = db.getAllStudents();
        StudentListAdapter adapter = new StudentListAdapter(list, student -> {
            Intent i = new Intent(SelectStudentActivity.this, SelectCoursesForStudentActivity.class);
            i.putExtra("student_id", student.getId());
            startActivity(i);
        });
        recycler.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
