package com.example.studentportal.ui.admin.adapters;

import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentportal.R;
import com.example.studentportal.models.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    public interface CourseActionListener {
        void onUpdate(Course c);
        void onDelete(Course c);
    }

    List<Course> list;
    CourseActionListener listener;

    public CourseAdapter(List<Course> list, CourseActionListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course c = list.get(position);

        holder.txtName.setText(c.getTitle());
        holder.txtInfo.setText("Code: " + c.getCourseCode() + " • Credit: " + c.getCredit());

        holder.btnUpdate.setOnClickListener(v -> listener.onUpdate(c));
        holder.btnDelete.setOnClickListener(v -> listener.onDelete(c));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtInfo;
        ImageButton btnUpdate, btnDelete;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtInfo = itemView.findViewById(R.id.txtInfo);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
