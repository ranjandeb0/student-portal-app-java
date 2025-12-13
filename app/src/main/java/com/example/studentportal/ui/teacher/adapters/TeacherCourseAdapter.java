package com.example.studentportal.ui.teacher.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentportal.R;
import com.example.studentportal.models.Course;

import java.util.List;

public class TeacherCourseAdapter
        extends RecyclerView.Adapter<TeacherCourseAdapter.VH> {

    public interface CourseClickListener {
        void onClick(Course course);
    }

    List<Course> list;
    CourseClickListener listener;

    public TeacherCourseAdapter(List<Course> list, CourseClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course_teacher, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int pos) {
        Course c = list.get(pos);
        h.txtTitle.setText(c.getTitle());
        h.txtCode.setText("Course Code: " + c.getCourseCode());
        h.txtCredit.setText("Credit: " + c.getCredit());

        h.itemView.setOnClickListener(v -> listener.onClick(c));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView txtTitle, txtCode, txtCredit;

        VH(View v) {
            super(v);
            txtTitle = v.findViewById(R.id.txtTitle);
            txtCode = v.findViewById(R.id.txtCode);
            txtCredit = v.findViewById(R.id.txtCredit);
        }
    }
}
