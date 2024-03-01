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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class profile extends AppCompatActivity {

    private ImageView back;
    private TextView nameTextView, schoolTextView, ageTextView, contactNumberTextView, addressTextView, logoutTextView,Username,pcourse;
    String id;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        id = getIntent().getStringExtra("StudentID");
        nameTextView = findViewById(R.id.lblname);
        pcourse = findViewById(R.id.courseid);
        schoolTextView = findViewById(R.id.lblschool);
        ageTextView = findViewById(R.id.lblage);
        contactNumberTextView = findViewById(R.id.lblcontact);
        addressTextView = findViewById(R.id.lbladdress);
        logoutTextView = findViewById(R.id.logout);
        back = findViewById(R.id.imageView3);
        Username = findViewById(R.id.ProfileUsername);

        displaydetails();

            // Logout functionality
            logoutTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(profile.this, login.class));
                    finish();
                }
            });

            // Back button functionality
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(profile.this, student_dash.class);
                    intent.putExtra("StudentID", id);
                    profile.this.startActivity(intent);
                    finish();
                }
            });
        }

        public void displaydetails(){

            databaseReference = FirebaseDatabase.getInstance().getReference("Student");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot classSnapshot : dataSnapshot.getChildren()) {
                        String Studentid = classSnapshot.child("id").getValue(String.class);

                        if (Studentid.equals(id)) {
                            Username.setText(classSnapshot.child("username").getValue(String.class));
                            nameTextView.setText(classSnapshot.child("name").getValue(String.class));
                            schoolTextView.setText(classSnapshot.child("school").getValue(String.class));
                            ageTextView.setText(classSnapshot.child("age").getValue(String.class));
                            contactNumberTextView.setText(classSnapshot.child("telephone").getValue(String.class));
                            addressTextView.setText(classSnapshot.child("address").getValue(String.class));
                            pcourse.setText(classSnapshot.child("course").getValue(String.class));

                            break;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(profile.this, "Failed to fetch data from Firebase", Toast.LENGTH_LONG).show();
                }
            });
        }

}