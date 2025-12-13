package com.example.studentportal.ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;

import com.example.studentportal.R;
import com.example.studentportal.database.MyDatabaseHelper;
import com.example.studentportal.models.Course;

public class AddCourseActivity extends AppCompatActivity {

    EditText edCourseName, edCourseCode, edCredit;
    Button btnSave;
    TextView txtTitle;
    MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Add Course");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        db = new MyDatabaseHelper(this);

        edCourseName = findViewById(R.id.edCourseName);
        edCourseCode = findViewById(R.id.edCourseCode);
        edCredit = findViewById(R.id.edCredit);
        txtTitle = findViewById(R.id.txtTitle);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> saveCourse());
    }

    private void saveCourse() {
        String name = edCourseName.getText().toString().trim();
        String code = edCourseCode.getText().toString().trim();
        String creditStr = edCredit.getText().toString().trim();

        if (name.isEmpty() || code.isEmpty() || creditStr.isEmpty()) {
            Toast.makeText(this, "All fields required!", Toast.LENGTH_SHORT).show();
            return;
        }

        double credit = Double.parseDouble(creditStr);

        Course course = new Course(0, name, code, credit, -1);

        if (db.addCourse(course)) {
            Toast.makeText(this, "Course Added", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
