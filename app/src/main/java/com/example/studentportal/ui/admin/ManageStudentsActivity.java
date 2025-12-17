package com.example.studentportal.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentportal.R;
import com.example.studentportal.database.MyDatabaseHelper;
import com.example.studentportal.models.Student;
import com.example.studentportal.ui.admin.adapters.StudentAdapter;

import java.util.List;

public class ManageStudentsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_students);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Manage Students");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        db = new MyDatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerStudents);

        loadStudents();
    }

    private void loadStudents() {
        List<Student> list = db.getAllStudents();

        StudentAdapter adapter = new StudentAdapter(list, new StudentAdapter.StudentActionListener() {
            @Override
            public void onUpdate(Student student) {
                Intent i = new Intent(ManageStudentsActivity.this, UpdateStudentActivity.class);
                i.putExtra("student_id", student.getId());
                startActivity(i);
            }

            @Override
            public void onDelete(Student student) {
                db.deleteStudent(student.getId());
                loadStudents(); // refresh list
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
