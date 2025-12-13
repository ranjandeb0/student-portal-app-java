package com.example.studentportal.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentportal.R;
import com.example.studentportal.database.MyDatabaseHelper;
import com.example.studentportal.models.Student;
import com.example.studentportal.models.Teacher;
import com.example.studentportal.ui.admin.AdminDashboardActivity;
import com.example.studentportal.ui.student.StudentDashboardActivity;
import com.example.studentportal.ui.teacher.TeacherDashboardActivity;

public class LoginActivity extends AppCompatActivity {

    EditText userName, password;
    Button loginButton;
    MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.user_name);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        db = new MyDatabaseHelper(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = userName.getText().toString().trim();
                String pass = password.getText().toString().trim();

                // ADMIN LOGIN
                if (user.equals("admin") && pass.equals("admin123")) {
                    Intent intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }

                // TEACHER LOGIN (using username)
                Teacher teacher = db.getTeacherByUsername(user);
                if (teacher != null && teacher.getPassword().equals(pass)) {
                    Intent intent = new Intent(LoginActivity.this, TeacherDashboardActivity.class);
                    intent.putExtra("teacher_id", teacher.getId());
                    startActivity(intent);
                    finish();
                    return;
                }

                // STUDENT LOGIN (using reg_id)
                Student student = db.getStudentByRegId(user);
                if (student != null && student.getPassword().equals(pass)) {
                    Intent intent = new Intent(LoginActivity.this, StudentDashboardActivity.class);
                    intent.putExtra("student_id", student.getId());
                    startActivity(intent);
                    finish();
                    return;
                }

                // FALLBACK
                Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
