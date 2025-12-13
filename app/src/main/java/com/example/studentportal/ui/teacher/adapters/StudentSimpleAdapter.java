package com.example.studentportal.ui.teacher.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.studentportal.R;
import com.example.studentportal.models.Student;

import java.util.List;

public class StudentSimpleAdapter
        extends RecyclerView.Adapter<StudentSimpleAdapter.VH> {

    public interface StudentClickListener {
        void onClick(Student student);
    }

    List<Student> list;
    StudentClickListener listener;

    public StudentSimpleAdapter(List<Student> list, StudentClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup p, int v) {
        return new VH(LayoutInflater.from(p.getContext())
                .inflate(R.layout.item_select_student, p, false));
    }

    @Override
    public void onBindViewHolder(VH h, int i) {
        Student s = list.get(i);
        h.txtName.setText(s.getName());
        h.txtReg.setText(s.getRegId());
        h.itemView.setOnClickListener(v -> listener.onClick(s));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView txtName, txtReg;
        VH(View v) {
            super(v);
            txtName = v.findViewById(R.id.txtStudentName);
            txtReg = v.findViewById(R.id.txtStudentReg);
        }
    }
}
