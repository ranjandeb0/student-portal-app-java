package com.example.studentportal.ui.admin.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentportal.R;
import com.example.studentportal.models.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    public interface StudentActionListener {
        void onUpdate(Student student);
        void onDelete(Student student);
    }

    private final List<Student> students;
    private final StudentActionListener listener;

    public StudentAdapter(List<Student> students, StudentActionListener listener) {
        this.students = students;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student s = students.get(position);

        holder.txtName.setText(s.getName());
        holder.txtRegId.setText("Reg: " + s.getRegId());

        holder.btnUpdate.setOnClickListener(v -> {
            if (listener != null) listener.onUpdate(s);
        });

        holder.btnDelete.setOnClickListener(v -> {
            if (listener != null) listener.onDelete(s);
        });
    }

    @Override
    public int getItemCount() {
        return students == null ? 0 : students.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtRegId;
        ImageButton btnUpdate, btnDelete;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtRegId = itemView.findViewById(R.id.txtRegId);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
