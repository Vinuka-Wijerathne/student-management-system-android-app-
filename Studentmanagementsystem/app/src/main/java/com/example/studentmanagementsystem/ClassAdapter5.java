package com.example.studentmanagementsystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassAdapter5 extends RecyclerView.Adapter<ClassAdapter5.ClassViewHolder>{

    private Context context;

    private ArrayList<noticemodel> notice;



    public ClassAdapter5(Context context, ArrayList<noticemodel> notice) {
            this.context = context;
            this.notice = notice;
            }

    public void setClassItems(ArrayList<noticemodel> newClassItems) {
            notice = newClassItems;
            notifyDataSetChanged();
            }

    @Override
    public ClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.res_stdash_notice, parent, false);
            return new ClassViewHolder(view);
            }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
            noticemodel notice1 = notice.get(position);


            holder.date.setText(notice1.getDate());
            holder.notice.setText(notice1.getMessage());

            }

    @Override
    public int getItemCount() {
            return notice.size();
            }

    public class ClassViewHolder extends RecyclerView.ViewHolder {
        LinearLayout rel;
        TextView notice,date;


        public ClassViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            notice = itemView.findViewById(R.id.notice123);
            rel = (LinearLayout) itemView.findViewById(R.id.lin4);

        }



    }
    }
