package com.example.studentmanagementsystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {

    private Context context;
    private ArrayList<Course> classItems;



    public ClassAdapter(Context context, ArrayList<Course> classItems) {
        this.context = context;
        this.classItems = classItems;
    }

    public void setClassItems(ArrayList<Course> newClassItems) {
        classItems = newClassItems;
        notifyDataSetChanged();
    }

    @Override
    public ClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ClassViewHolder(view);
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
            classTextView = itemView.findViewById(R.id.class_tv);
            rel = (RelativeLayout) itemView.findViewById(R.id.relat);

            rel.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Studentregistry.class);
                    intent.putExtra("subjectName", classTextView.getText().toString());
                    context.startActivity(intent);
                    Toast.makeText(context.getApplicationContext(), "Hello" , Toast.LENGTH_SHORT).show();
                }
            });
        }



    }
}