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

public class Studentregistry extends AppCompatActivity {

    ImageView back ;
    String subjectName;
    RecyclerView recyclerView;
    ClassAdapter2 classAdapter;
    ArrayList<Students> students;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentregistry);
        back=  findViewById(R.id.backbtnimg);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        students = new ArrayList<>();
        classAdapter = new ClassAdapter2( this, students);
        recyclerView.setAdapter(classAdapter);
        subjectName = getIntent().getStringExtra("subjectName");

        databaseReference = FirebaseDatabase.getInstance().getReference("Student");

        // Retrieve data from Firebase and update the RecyclerView
        fetchDataFromFirebase();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to UpdateActivity (replace with your actual activity name)
                Intent intent = new Intent(Studentregistry.this,attendance.class);
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
                Toast.makeText(Studentregistry.this, "Failed to fetch data from Firebase", LENGTH_SHORT).show();
            }
        });
    }
}