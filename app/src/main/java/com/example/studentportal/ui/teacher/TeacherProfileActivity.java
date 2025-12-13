package com.example.studentportal.ui.teacher;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentportal.R;
import com.example.studentportal.database.MyDatabaseHelper;
import com.example.studentportal.models.Teacher;

public class TeacherProfileActivity extends AppCompatActivity {

    EditText edName, edEmail;
    TextView tvUsername;
    MyDatabaseHelper db;
    Teacher teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);

        getSupportActionBar().setTitle("My Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int teacherId = getIntent().getIntExtra("teacher_id", -1);

        db = new MyDatabaseHelper(this);
        teacher = db.getTeacherById(teacherId);

        edName = findViewById(R.id.edName);
        edEmail = findViewById(R.id.edEmail);
        tvUsername = findViewById(R.id.tvUsername);

        edName.setText(teacher.getName());
        edEmail.setText(teacher.getEmail());
        tvUsername.setText("Username: " + teacher.getUsername());

        findViewById(R.id.btnUpdate).setOnClickListener(v -> {
            teacher.setName(edName.getText().toString());
            teacher.setEmail(edEmail.getText().toString());

            db.updateTeacher(teacher);
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
