package com.example.studentportal.ui.admin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentportal.R;
import com.example.studentportal.database.MyDatabaseHelper;
import com.example.studentportal.models.Student;

public class UpdateStudentActivity extends AppCompatActivity {

    EditText edName, edEmail, edPhone, edPassword, edRegId;
    Button btnSave;
    TextView titleText;

    MyDatabaseHelper db;
    int studentId;
    Student currentStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);  // reuse the same layout
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Update Student");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        db = new MyDatabaseHelper(this);

        // Get UI references
        edName = findViewById(R.id.edName);
        edEmail = findViewById(R.id.edEmail);
        edPhone = findViewById(R.id.edPhone);
        edPassword = findViewById(R.id.edPassword);
        edRegId = findViewById(R.id.edRegId);
        btnSave = findViewById(R.id.btnSave);
        titleText = findViewById(R.id.txtTitle);  // we will add this to reused layout

        // Change UI text
        titleText.setText("Update Student");
        btnSave.setText("Update");

        // Get the student ID from Intent
        studentId = getIntent().getIntExtra("student_id", -1);
        if (studentId == -1) {
            Toast.makeText(this, "Invalid student", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Load student from DB
        currentStudent = db.getStudentById(studentId);
        if (currentStudent == null) {
            Toast.makeText(this, "Student not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Pre-fill fields
        edName.setText(currentStudent.getName());
        edEmail.setText(currentStudent.getEmail());
        edPhone.setText(currentStudent.getPhone());
        edPassword.setText(currentStudent.getPassword());
        edRegId.setText(currentStudent.getRegId());

        // Handle update click
        btnSave.setOnClickListener(v -> updateStudent());
    }

    private void updateStudent() {
        // Update object
        currentStudent.setName(edName.getText().toString());
        currentStudent.setEmail(edEmail.getText().toString());
        currentStudent.setPhone(edPhone.getText().toString());
        currentStudent.setPassword(edPassword.getText().toString());
        currentStudent.setRegId(edRegId.getText().toString());

        boolean ok = db.updateStudent(currentStudent);

        if (ok) {
            Toast.makeText(this, "Updated successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show();
        }
    }
}
