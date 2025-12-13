package com.example.studentportal.ui.admin.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentportal.R;
import com.example.studentportal.models.Student;

import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.VH> {

    public interface StudentClick {
        void onStudentClick(Student student);
    }

    private final List<Student> list;
    private final StudentClick listener;

    public StudentListAdapter(List<Student> list, StudentClick listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_student, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Student s = list.get(position);
        holder.name.setText(s.getName());
        holder.reg.setText("Reg: " + s.getRegId());
        holder.itemView.setOnClickListener(v -> listener.onStudentClick(s));
    }

    @Override
    public int getItemCount() { return list == null ? 0 : list.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView name, reg;
        VH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtStudentName);
            reg = itemView.findViewById(R.id.txtStudentReg);
        }
    }
}
