package com.example.studentportal.ui.student.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.studentportal.R;

import java.util.List;
import java.util.Map;

public class StudentCourseAdapter
        extends RecyclerView.Adapter<StudentCourseAdapter.VH> {

    List<Map<String, String>> list;

    public StudentCourseAdapter(List<Map<String, String>> list) {
        this.list = list;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup p, int v) {
        return new VH(LayoutInflater.from(p.getContext())
                .inflate(R.layout.item_student_course, p, false));
    }

    @Override
    public void onBindViewHolder(VH h, int i) {
        Map<String, String> m = list.get(i);
        h.txtCourse.setText(m.get("course"));
        h.txtResult.setText(m.get("result"));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView txtCourse, txtResult;
        VH(View v) {
            super(v);
            txtCourse = v.findViewById(R.id.txtCourse);
            txtResult = v.findViewById(R.id.txtResult);
        }
    }
}
