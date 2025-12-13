package com.example.studentportal.ui.student.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentportal.R;
import com.example.studentportal.models.Course;
import com.example.studentportal.models.Result;

import java.util.List;
import java.util.Map;

public class StudentCourseAdapter
        extends RecyclerView.Adapter<StudentCourseAdapter.VH> {

    List<Map<Course, Result>> list;

    public StudentCourseAdapter(List<Map<Course, Result>> list) {
        this.list = list;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup p, int v) {
        return new VH(LayoutInflater.from(p.getContext())
                .inflate(R.layout.item_student_course, p, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int i) {
        Map<Course, Result> map = list.get(i);

        // Each map has exactly one entry
        Map.Entry<Course, Result> entry = map.entrySet().iterator().next();

        Course course = entry.getKey();
        Result result = entry.getValue();

        h.txtCourse.setText(course.getTitle());
        h.txtCode.setText("Course Code: " + course.getCourseCode());
        h.txtCredit.setText("Credit: " + course.getCredit());

        if (result == null) {
            h.txtResult.setText("Pending");
            h.txtResult.setText("Pending");
        } else {
            h.txtGrade.setText("Grade: " + result.getGrade());
            h.txtResult.setText("Marks: " + result.getMarks());
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView txtCourse, txtResult, txtCode, txtCredit, txtGrade;
        VH(View v) {
            super(v);
            txtCourse = v.findViewById(R.id.txtCourse);
            txtResult = v.findViewById(R.id.txtResult);
            txtGrade = v.findViewById(R.id.txtGrade);
            txtCode = v.findViewById(R.id.txtCode);
            txtCredit = v.findViewById(R.id.txtCredit);
        }
    }
}
