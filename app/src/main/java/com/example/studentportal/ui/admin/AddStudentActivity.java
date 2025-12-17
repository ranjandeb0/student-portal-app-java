package com.example.studentportal.ui.admin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentportal.R;
import com.example.studentportal.database.MyDatabaseHelper;
import com.example.studentportal.models.Student;

public class AddStudentActivity extends AppCompatActivity {

    EditText name, email, phone, password, regId;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Add Student");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        name = findViewById(R.id.edName);
        email = findViewById(R.id.edEmail);
        phone = findViewById(R.id.edPhone);
        password = findViewById(R.id.edPassword);
        regId = findViewById(R.id.edRegId);
        saveBtn = findViewById(R.id.btnSave);

        MyDatabaseHelper db = new MyDatabaseHelper(this);

        saveBtn.setOnClickListener(v -> {
            Student s = new Student(
                    0,
                    name.getText().toString(),
                    email.getText().toString(),
                    phone.getText().toString(),
                    password.getText().toString(),
                    regId.getText().toString()
            );

            boolean success = db.addStudent(s);
            Toast.makeText(this, success ? "Student Added" : "Failed", Toast.LENGTH_SHORT).show();
            if (success) finish();
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}