package com.example.studentmanagementsystem;

import static android.widget.Toast.LENGTH_SHORT;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.ArrayList;

public class ClassAdapter4 extends RecyclerView.Adapter<ClassAdapter4.MyViewHolder> {

    static Context context;
    static String subjectName;
    private ArrayList<Students> students;

    public ClassAdapter4(Context context, ArrayList<Students> students,String subjectName) {
        this.context = context;
        this.students = students;
        this.subjectName = subjectName;
    }

    public void setClassItems(ArrayList<Students> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ClassAdapter4.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.res_update_by_student, parent, false);
        return new ClassAdapter4.MyViewHolder(view);
    }


    public void onBindViewHolder(@NonNull ClassAdapter4.MyViewHolder holder, int position) {
        Students student = students.get(position);

        // Display only ID and Name
        holder.textViewId.setText(student.getID());
        holder.textViewName.setText(student.getName());

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId;
        TextView textViewName;
        LinearLayout lin;
        Button btn , btndelate;
        String subject;

        DatabaseReference databaseReference;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.textID);
            textViewName = itemView.findViewById(R.id.textViewName);
            lin = (LinearLayout) itemView.findViewById(R.id.relat2);
            btn = itemView.findViewById(R.id.btnupdate1);
            btndelate = itemView.findViewById(R.id.delete);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, update.class);
                    intent.putExtra("StudentID", textViewId.getText().toString());
                    context.startActivity(intent);

                }
            });

            btndelate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    deleteid();
                    Toast.makeText(context, "Deleted!!", LENGTH_SHORT).show();
                    Intent intent = new Intent(context, update_by_student.class);
                    intent.putExtra("subjectName", subjectName);
                    context.startActivity(intent);
                }
            });



        }
        private void deleteid(){
            databaseReference = FirebaseDatabase.getInstance().getReference("Student");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot classSnapshot : snapshot.getChildren()) {
                        String StudentID = classSnapshot.child("id").getValue(String.class);

                        if(StudentID.equals(textViewId.getText().toString())){
                            classSnapshot.getRef().removeValue();
                            break;

                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }

}
