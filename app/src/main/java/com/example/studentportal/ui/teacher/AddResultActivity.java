package com.example.studentportal.ui.teacher;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentportal.R;
import com.example.studentportal.database.MyDatabaseHelper;

public class AddResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_result);

        getSupportActionBar().setTitle("Assign Result");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int studentId = getIntent().getIntExtra("student_id", -1);
        int courseId = getIntent().getIntExtra("course_id", -1);

        EditText edMarks = findViewById(R.id.edMarks);
        EditText edGrade = findViewById(R.id.edGrade);

        MyDatabaseHelper db = new MyDatabaseHelper(this);

        findViewById(R.id.btnSave).setOnClickListener(v -> {
            double marks = Double.parseDouble(edMarks.getText().toString());
            String grade = edGrade.getText().toString();

            db.saveOrUpdateResult(studentId, courseId, marks, grade);
            Toast.makeText(this, "Result Saved", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
