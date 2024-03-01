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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class update_by_sub extends AppCompatActivity {

    ImageView back;
    RecyclerView recyclerView;
    ClassAdapter3 classAdapter;
    ArrayList<Course> classItems;
    DatabaseReference databaseReference;
    ImageView backbtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_by_sub);

        recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        backbtn = findViewById(R.id.imgback2);

        classItems = new ArrayList<>();
        classAdapter = new ClassAdapter3(this, classItems);
        recyclerView.setAdapter(classAdapter);


        databaseReference = FirebaseDatabase.getInstance().getReference("courses");

        fetchDataFromFirebase();

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(update_by_sub.this, MainActivity.class);
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
                Toast.makeText(update_by_sub.this, "Failed to fetch data from Firebase", LENGTH_SHORT).show();
            }
        });
    }

}