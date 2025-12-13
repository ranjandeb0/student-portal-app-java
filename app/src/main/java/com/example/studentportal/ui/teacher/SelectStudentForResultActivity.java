package com.example.studentportal.ui.teacher;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentportal.R;
import com.example.studentportal.database.MyDatabaseHelper;
import com.example.studentportal.models.Student;
import com.example.studentportal.ui.teacher.adapters.StudentSimpleAdapter;

import java.util.List;

public class SelectStudentForResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_student_for_result);

        getSupportActionBar().setTitle("Select Student");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int courseId = getIntent().getIntExtra("course_id", -1);

        RecyclerView recycler = findViewById(R.id.recyclerStudents);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        List<Student> students = db.getStudentsByCourse(courseId);

        recycler.setAdapter(new StudentSimpleAdapter(students, student -> {
            Intent i = new Intent(this, AddResultActivity.class);
            i.putExtra("student_id", student.getId());
            i.putExtra("course_id", courseId);
            startActivity(i);
        }));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
