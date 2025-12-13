package com.example.studentportal.ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentportal.R;
import com.example.studentportal.database.MyDatabaseHelper;
import com.example.studentportal.models.Teacher;

public class UpdateTeacherActivity extends AppCompatActivity {

    EditText edName, edEmail, edPhone, edPassword, edUsername;
    Button btnSave;
    TextView txtTitle;

    MyDatabaseHelper db;
    int teacherId;
    Teacher currentTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher); // same layout reused

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Update Teacher");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        db = new MyDatabaseHelper(this);

        // UI references
        txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText("Update Teacher");

        edName = findViewById(R.id.edName);
        edEmail = findViewById(R.id.edEmail);
        edPhone = findViewById(R.id.edPhone);
        edPassword = findViewById(R.id.edPassword);
        edUsername = findViewById(R.id.edUsername);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setText("Update");

        // Retrieve teacher ID from intent
        teacherId = getIntent().getIntExtra("teacher_id", -1);
        if (teacherId == -1) {
            Toast.makeText(this, "Invalid teacher!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        loadTeacherData();

        btnSave.setOnClickListener(v -> updateTeacher());
    }

    private void loadTeacherData() {
        currentTeacher = db.getTeacherById(teacherId);

        if (currentTeacher == null) {
            Toast.makeText(this, "Teacher not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Pre-fill fields
        edName.setText(currentTeacher.getName());
        edEmail.setText(currentTeacher.getEmail());
        edPhone.setText(currentTeacher.getPhone());
        edPassword.setText(currentTeacher.getPassword());
        edUsername.setText(currentTeacher.getUsername());
    }

    private void updateTeacher() {
        currentTeacher.setName(edName.getText().toString().trim());
        currentTeacher.setEmail(edEmail.getText().toString().trim());
        currentTeacher.setPhone(edPhone.getText().toString().trim());
        currentTeacher.setPassword(edPassword.getText().toString().trim());
        currentTeacher.setUsername(edUsername.getText().toString().trim());

        boolean ok = db.updateTeacher(currentTeacher);

        if (ok) {
            Toast.makeText(this, "Teacher updated successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
