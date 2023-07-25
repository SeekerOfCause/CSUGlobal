package com.csc475.studentdata.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.csc475.studentdata.R;
import com.csc475.studentdata.database.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private Context context;
    private List<Student> studentList;

    public StudentAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater view = LayoutInflater.from(parent.getContext());
        View itemView = view.inflate(R.layout.cardview_student, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);

        // Bind the student data to the CardView views
        holder.firstNameTextView.setText(student.getFirstName());
        holder.lastNameTextView.setText(student.getLastName());
        holder.gradeTextView.setText(String.valueOf(student.getGrade()));
        holder.idTextView.setText(String.valueOf(student.getId()));
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView firstNameTextView;
        TextView lastNameTextView;
        TextView gradeTextView;
        TextView idTextView;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            firstNameTextView = itemView.findViewById(R.id.card_first_name);
            lastNameTextView = itemView.findViewById(R.id.card_last_name);
            gradeTextView = itemView.findViewById(R.id.card_grade);
            idTextView = itemView.findViewById(R.id.card_id);
        }
    }
}

