package com.example.studentportal.ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;

import com.example.studentportal.R;
import com.example.studentportal.database.MyDatabaseHelper;
import com.example.studentportal.models.Course;

public class UpdateCourseActivity extends AppCompatActivity {

    EditText edCourseName, edCourseCode, edCredit;
    Button btnSave;
    TextView txtTitle;

    MyDatabaseHelper db;
    int courseId;
    Course currentCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Update Course");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        db = new MyDatabaseHelper(this);

        edCourseName = findViewById(R.id.edCourseName);
        edCourseCode = findViewById(R.id.edCourseCode);
        edCredit = findViewById(R.id.edCredit);
        txtTitle = findViewById(R.id.txtTitle);
        btnSave = findViewById(R.id.btnSave);

        txtTitle.setText("Update Course");
        btnSave.setText("Update");

        courseId = getIntent().getIntExtra("course_id", -1);

        loadData();

        btnSave.setOnClickListener(v -> update());
    }

    private void loadData() {
        currentCourse = db.getCourseById(courseId);

        if (currentCourse == null) {
            Toast.makeText(this, "Course not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        edCourseName.setText(currentCourse.getTitle());
        edCourseCode.setText(currentCourse.getCourseCode());
        edCredit.setText(String.valueOf(currentCourse.getCredit()));
    }

    private void update() {
        currentCourse.setTitle(edCourseName.getText().toString());
        currentCourse.setCourseCode(edCourseCode.getText().toString());
        currentCourse.setCredit(Double.parseDouble(edCredit.getText().toString()));

        if (db.updateCourse(currentCourse)) {
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
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
