package com.example.studentmanagementsystem;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.ArrayList;

public class ClassAdapter2 extends RecyclerView.Adapter<ClassAdapter2.MyViewHolder> {

    static Context context;
    private ArrayList<Students> students;
    static boolean isalreadyadded ;

    public ClassAdapter2(Context context,ArrayList<Students> students) {
        this.context = context;
        this.students = students;
    }

    public void setClassItems(ArrayList<Students> students) {
        this.students = students;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendancelist, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Students student = students.get(position);

        // Display only ID and Name
        holder.textViewId.setText( student.getID());
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
        Button btn;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.textView12);
            textViewName = itemView.findViewById(R.id.textView13);
            lin = (LinearLayout) itemView.findViewById(R.id.relat2);
            btn = itemView.findViewById(R.id.btnpresent);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // Assuming you have a 'Subject' node in your Firebase database
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("MarkAttendance");

                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            for (DataSnapshot classSnapshot : snapshot.getChildren()) {
                                isalreadyadded  = false;
                                if(textViewId.getText().toString().equals(classSnapshot.child("id").getValue(String.class)) &&
                                        LocalDate.now().toString().equals(classSnapshot.child("date").getValue(String.class))){

                                    isalreadyadded=true;
                                    break;

                                }


                                }
                            }




                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    markAttendence s = null;
                    if(!isalreadyadded) {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            s = new markAttendence(textViewId.getText().toString(), textViewName.getText().toString(),
                                    LocalDate.now().toString(), "Present");
                        }
                        databaseReference.push().setValue(s);
                        Toast.makeText(context.getApplicationContext(), "Marked " + textViewId.getText().toString(), LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context.getApplicationContext(), "AlreadyAdded " + textViewId.getText().toString(), LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
}