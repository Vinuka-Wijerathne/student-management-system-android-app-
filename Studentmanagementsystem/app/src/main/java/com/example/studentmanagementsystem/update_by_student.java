package com.example.studentmanagementsystem;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class update_by_student extends AppCompatActivity {

    ImageView back;
    String subjectName;
    RecyclerView recyclerView;
    ClassAdapter4 classAdapter;
    ArrayList<Students> students;
    DatabaseReference databaseReference;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_by_student);

        back = findViewById(R.id.upback);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        subjectName = getIntent().getStringExtra("subjectName");
        students = new ArrayList<>();
        classAdapter = new ClassAdapter4( this, students,subjectName);
        recyclerView.setAdapter(classAdapter);


        databaseReference = FirebaseDatabase.getInstance().getReference("Student");

        // Retrieve data from Firebase and update the RecyclerView
        fetchDataFromFirebase();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the main activity
                Intent intent = new Intent(update_by_student.this, update_by_sub.class);
                startActivity(intent);
            }
        });
    }
    private void fetchDataFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                students.clear(); // Clear existing data to avoid duplication

                for (DataSnapshot classSnapshot : dataSnapshot.getChildren()) {
                    String ID = classSnapshot.child("id").getValue(String.class);
                    String Name = classSnapshot.child("name").getValue(String.class);
                    String subject = classSnapshot.child("course").getValue(String.class);

                    if (ID != null && Name != null) {

                        if (subject.equalsIgnoreCase(subjectName)) {

                            Students students1 = new Students();
                            students1.setID(ID);
                            students1.setCourse(subject);
                            students1.setName(Name);

                            students.add(students1);
                        }

                    }

                    classAdapter.setClassItems(students);

                }

                // Update the RecyclerView with the ArrayList
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Firebase", "Error: " + databaseError.getMessage());
                Toast.makeText(update_by_student.this, "Failed to fetch data from Firebase", LENGTH_SHORT).show();
            }
        });
    }
}