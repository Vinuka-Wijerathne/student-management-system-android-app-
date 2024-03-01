package com.example.studentmanagementsystem;

import static android.widget.Toast.LENGTH_SHORT;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class student_dash extends AppCompatActivity {

    private RecyclerView recyclerView,recyclerView2;

    ArrayList<noticemodel> notices;

    ArrayList<markAttendence> mark;
    ClassAdapter5 classAdapter;

    ClassAdapter6 classAdapter2;
    private ImageView profileImageView;
    String id;
    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dash);

        // Initialize TextView, ImageView, and Logout Button
        id = getIntent().getStringExtra("StudentID");
        profileImageView = findViewById(R.id.profileimg);

//initialize and set up the notices recycleview
        recyclerView = findViewById(R.id.notice12);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notices = new ArrayList<>();
        classAdapter = new ClassAdapter5(this, notices);
        recyclerView.setAdapter(classAdapter);
//fetch notices data from notices
         getNoticesFromFirebase();
//initialize and set up the attendance history
        recyclerView2 = findViewById(R.id.attendencehis);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        mark = new ArrayList<>();
        classAdapter2 = new ClassAdapter6(this, mark);
        recyclerView2.setAdapter(classAdapter2);
   //fetch attendence
        getAttendenceFromFirebase();

        // Load profile image from Firebase (Replace "userImageUrl" with the actual key in Firebase)
        loadProfileImageFromFirebase("userImageUrl");

        // Set click event for the profile image
        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open profile activity when the profile image is clicked
                Intent intent = new Intent(student_dash.this, profile.class);
                intent.putExtra("StudentID", id);
                student_dash.this.startActivity(intent);
            }
        });


    }

    // Replace this with your actual Firebase code to fetch notices
    private void getAttendenceFromFirebase() {
        databaseReference = FirebaseDatabase.getInstance().getReference("MarkAttendance");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notices.clear(); // Clear existing data to avoid duplication
                ArrayList<markAttendence> classItems = new ArrayList<>();

                for (DataSnapshot classSnapshot : dataSnapshot.getChildren()) {

                    String Studentid = classSnapshot.child("id").getValue(String.class);
                    if (Studentid.equals(id) ) {

                        String date = classSnapshot.child("date").getValue(String.class);
                        String Message = classSnapshot.child("mark").getValue(String.class);
                        String name = classSnapshot.child("name").getValue(String.class);

                        markAttendence model = new markAttendence(Studentid,name,date,Message);
                        classItems.add(model);

                        classAdapter2.setClassItems(classItems);
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Firebase", "Error: " + databaseError.getMessage());
                Toast.makeText(student_dash.this, "Failed to fetch data from Firebase", LENGTH_SHORT).show();
            }
        });
    }

    // Replace this with your actual Firebase code to fetch the user's profile image URL
    private void loadProfileImageFromFirebase(String userImageUrlKey) {
        DatabaseReference userImageRef = FirebaseDatabase.getInstance().getReference().child(userImageUrlKey);

        userImageRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String imageUrl = dataSnapshot.getValue(String.class);

                // Load the profile image into ImageView using Picasso
                if (imageUrl != null && !imageUrl.isEmpty()) {
                    Picasso.get().load(imageUrl).into(profileImageView);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
            }
        });
    }
    private void getNoticesFromFirebase(){
        databaseReference = FirebaseDatabase.getInstance().getReference("Notice");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notices.clear(); // Clear existing data to avoid duplication
                ArrayList<noticemodel> classItems = new ArrayList<>();

                for (DataSnapshot classSnapshot : dataSnapshot.getChildren()) {
                    String date = classSnapshot.child("date").getValue(String.class);
                    String Message = classSnapshot.child("message").getValue(String.class);
                    if (Message != null ) {
                        noticemodel model = new noticemodel(date,Message);
                        classItems.add(model);

                        classAdapter.setClassItems(classItems);
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Firebase", "Error: " + databaseError.getMessage());
                Toast.makeText(student_dash.this, "Failed to fetch data from Firebase", LENGTH_SHORT).show();
            }
        });
    }

}
