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

public class AddTeacherActivity extends AppCompatActivity {

    EditText edName, edEmail, edPhone, edPassword, edUsername;
    Button btnSave;
    TextView txtTitle;

    MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher); // your updated layout

        // Enable back button in action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Add Teacher");
        }

        db = new MyDatabaseHelper(this);

        txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText("Add Teacher");

        edName = findViewById(R.id.edName);
        edEmail = findViewById(R.id.edEmail);
        edPhone = findViewById(R.id.edPhone);
        edPassword = findViewById(R.id.edPassword);
        edUsername = findViewById(R.id.edUsername); // reusing regId field as "username"

        btnSave = findViewById(R.id.btnSave);
        btnSave.setText("Save Teacher");

        btnSave.setOnClickListener(v -> saveTeacher());
    }

    private void saveTeacher() {
        String name = edName.getText().toString().trim();
        String email = edEmail.getText().toString().trim();
        String phone = edPhone.getText().toString().trim();
        String password = edPassword.getText().toString().trim();
        String username = edUsername.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || username.isEmpty()) {
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
            return;
        }

        Teacher teacher = new Teacher(0, name, email, phone, password, username);

        boolean ok = db.addTeacher(teacher);

        if (ok) {
            Toast.makeText(this, "Teacher added successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Failed to add teacher", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
