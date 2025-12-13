package com.example.studentportal.ui.admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentportal.R;
import com.example.studentportal.database.MyDatabaseHelper;
import com.example.studentportal.models.Teacher;
import com.example.studentportal.ui.admin.adapters.TeacherListAdapter;

import java.util.List;

public class SelectTeacherActivity extends AppCompatActivity {

    RecyclerView recycler;
    MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_student); // reuse layout (list)

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Select Teacher");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recycler = findViewById(R.id.recyclerStudents);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        db = new MyDatabaseHelper(this);

        List<Teacher> list = db.getAllTeachers();
        TeacherListAdapter adapter = new TeacherListAdapter(list, teacher -> {
            Intent i = new Intent(SelectTeacherActivity.this, SelectCoursesForTeacherActivity.class);
            i.putExtra("teacher_id", teacher.getId());
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
