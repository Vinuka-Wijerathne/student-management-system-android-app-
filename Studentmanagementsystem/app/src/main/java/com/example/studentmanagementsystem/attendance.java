package com.example.studentmanagementsystem;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class attendance extends AppCompatActivity {

    ImageView imageView;
    RecyclerView recyclerView;
    ClassAdapter classAdapter;
    ArrayList<Course> classItems;
    DatabaseReference databaseReference;
    Map<String,String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        imageView = findViewById(R.id.imageView);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        classItems = new ArrayList<>();
        classAdapter = new ClassAdapter(this, classItems);
        recyclerView.setAdapter(classAdapter);

        // Assuming you have a 'Subject' node in your Firebase database
        /*databaseReference = FirebaseDatabase.getInstance().getReference("Student");

        Students s = new Students("123","Shafry" , "24" , "STC" , "071354521" , "Maths");
        databaseReference.push().setValue(s);*/



        databaseReference = FirebaseDatabase.getInstance().getReference("courses");

        // Retrieve data from Firebase and update the RecyclerView
        fetchDataFromFirebase();


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to UpdateActivity (replace with your actual activity name)
                Intent intent = new Intent(attendance.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }



    private void fetchDataFromFirebase() {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    classItems.clear(); // Clear existing data to avoid duplication
                    ArrayList<Course> classItems = new ArrayList<>();

                    for (DataSnapshot classSnapshot : dataSnapshot.getChildren()) {
                        String courseName = classSnapshot.child("courseName").getValue(String.class);

                        if (courseName != null ) {
                            Course cname = new Course(courseName);
                            classItems.add(cname);

                            classAdapter.setClassItems(classItems);
                        }

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e("Firebase", "Error: " + databaseError.getMessage());
                    Toast.makeText(attendance.this, "Failed to fetch data from Firebase", LENGTH_SHORT).show();
                }
            });
        }

    }