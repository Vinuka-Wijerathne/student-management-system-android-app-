package com.example.studentmanagementsystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassAdapter3 extends RecyclerView.Adapter<ClassAdapter3.ClassViewHolder> {

    private Context context;
    private ArrayList<Course> classItems;



    public ClassAdapter3(Context context, ArrayList<Course> classItems) {
        this.context = context;
        this.classItems = classItems;
    }

    public void setClassItems(ArrayList<Course> newClassItems) {
        classItems = newClassItems;
        notifyDataSetChanged();
    }

    @Override
    public ClassAdapter3.ClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.res_update_by_sub, parent, false);
        return new ClassAdapter3.ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        Course classItem = classItems.get(position);

        holder.classTextView.setText(classItem.getCourseName());

    }

    @Override
    public int getItemCount() {
        return classItems.size();
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder {
        TextView classTextView;
        RelativeLayout rel;

        public ClassViewHolder(View itemView) {
            super(itemView);
            classTextView = itemView.findViewById(R.id.sub_up);
            rel = (RelativeLayout) itemView.findViewById(R.id.relat_up);

            rel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, update_by_student.class);
                    intent.putExtra("subjectName", classTextView.getText().toString());
                    context.startActivity(intent);

                }
            });
        }



    }
}