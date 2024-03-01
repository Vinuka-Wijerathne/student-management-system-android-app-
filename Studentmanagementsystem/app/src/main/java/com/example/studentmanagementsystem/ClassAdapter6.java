package com.example.studentmanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassAdapter6 extends RecyclerView.Adapter<ClassAdapter6.ClassViewHolder>{

    private Context context;

    private ArrayList<markAttendence> mark;



    public ClassAdapter6(Context context, ArrayList<markAttendence> notice) {
        this.context = context;
        this.mark = notice;
    }

    public void setClassItems(ArrayList<markAttendence> newClassItems) {
        mark = newClassItems;
        notifyDataSetChanged();
    }

    @Override
    public ClassAdapter6.ClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.res_stdash_att_his, parent, false);
        return new ClassAdapter6.ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassAdapter6.ClassViewHolder holder, int position) {
        markAttendence notice1 = mark.get(position);


        holder.date.setText(notice1.getDate());
        holder.text.setText(notice1.getMark());

    }

    @Override
    public int getItemCount() {
        return mark.size();
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder {
        LinearLayout rel;
        TextView text,date;


        public ClassViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date2);
            text = itemView.findViewById(R.id.atte2);
            rel = (LinearLayout) itemView.findViewById(R.id.lin5);

        }



    }
}

