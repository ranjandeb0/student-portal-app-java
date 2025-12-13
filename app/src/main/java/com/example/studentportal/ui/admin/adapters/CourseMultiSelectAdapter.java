package com.example.studentportal.ui.admin.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentportal.R;
import com.example.studentportal.models.Course;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CourseMultiSelectAdapter extends RecyclerView.Adapter<CourseMultiSelectAdapter.VH> {

    public interface SelectionListener {
        void onSelectionChanged(Set<Integer> selectedIds);
    }

    private final List<Course> list;
    private final Set<Integer> selected = new HashSet<>();
    private final SelectionListener listener;

    public CourseMultiSelectAdapter(List<Course> list, SelectionListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course_multiselect, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Course c = list.get(position);
        holder.title.setText(c.getTitle());
        holder.meta.setText(c.getCourseCode() + " • " + c.getCredit());
        holder.checkBox.setChecked(selected.contains(c.getCourseId()));

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) selected.add(c.getCourseId());
            else selected.remove(c.getCourseId());
            if (listener != null) listener.onSelectionChanged(selected);
        });

        // click whole row toggles
        holder.itemView.setOnClickListener(v -> {
            boolean now = !holder.checkBox.isChecked();
            holder.checkBox.setChecked(now);
        });
    }

    @Override
    public int getItemCount() { return list == null ? 0 : list.size(); }

    public Set<Integer> getSelectedIds() { return selected; }

    static class VH extends RecyclerView.ViewHolder {
        TextView title, meta;
        CheckBox checkBox;
        VH(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txtCourseTitle);
            meta = itemView.findViewById(R.id.txtCourseMeta);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}
