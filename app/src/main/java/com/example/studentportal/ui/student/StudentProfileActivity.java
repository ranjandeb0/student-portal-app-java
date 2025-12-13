package com.example.studentportal.ui.student;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentportal.R;
import com.example.studentportal.database.MyDatabaseHelper;
import com.example.studentportal.models.Student;

public class StudentProfileActivity extends AppCompatActivity {

    EditText edName, edEmail, edPhone;
    TextView tvRegId;
    MyDatabaseHelper db;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        getSupportActionBar().setTitle("My Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int studentId = getIntent().getIntExtra("student_id", -1);

        db = new MyDatabaseHelper(this);
        student = db.getStudentById(studentId);

        edName = findViewById(R.id.edName);
        edEmail = findViewById(R.id.edEmail);
        edPhone = findViewById(R.id.edPhone);
        tvRegId = findViewById(R.id.tvRegId);

        edName.setText(student.getName());
        edEmail.setText(student.getEmail());
        edPhone.setText(student.getPhone());
        tvRegId.setText(student.getRegId());

        findViewById(R.id.btnUpdate).setOnClickListener(v -> {
            student.setName(edName.getText().toString());
            student.setEmail(edEmail.getText().toString());
            student.setPhone(edPhone.getText().toString());

            db.updateStudent(student);
            Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}