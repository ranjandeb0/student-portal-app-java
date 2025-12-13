package com.example.studentportal.ui.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.studentportal.R;
import com.example.studentportal.database.MyDatabaseHelper;
import com.example.studentportal.models.Teacher;
import com.example.studentportal.ui.admin.adapters.TeacherAdapter;

import java.util.List;

public class ManageTeachersActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_teachers);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Manage Teachers");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        db = new MyDatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerTeachers);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadTeachers();
    }

    private void loadTeachers() {
        List<Teacher> list = db.getAllTeachers();

        TeacherAdapter adapter = new TeacherAdapter(list, new TeacherAdapter.TeacherActionListener() {
            @Override
            public void onUpdate(Teacher teacher) {
                Intent i = new Intent(ManageTeachersActivity.this, UpdateTeacherActivity.class);
                i.putExtra("teacher_id", teacher.getId());
                startActivity(i);
            }

            @Override
            public void onDelete(Teacher teacher) {
                db.deleteTeacher(teacher.getId());
                loadTeachers(); // refresh list
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
