package com.example.studentmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_course extends AppCompatActivity {

    private EditText courseNameEditText;
    private Button addCourseButton ;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("courses");

        // Initialize UI components
        courseNameEditText = findViewById(R.id.txtaddcourse2);
        addCourseButton = findViewById(R.id.coursebtn);

        // Set onClickListener for the "Add Course" button
        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCourseToDatabase();
            }
        });

        // Initialize ImageView
        ImageView backToMainImageView = findViewById(R.id.backbtn);

        // Set onClickListener for the ImageView to go back to MainActivity
        backToMainImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to go to MainActivity
                Intent intent = new Intent(Add_course.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close the current activity
            }
        });

    }

    private void addCourseToDatabase() {
        // Get the course name from the EditText
        Course course = new Course(courseNameEditText.getText().toString());
        // Check if the course name is not empty
        databaseReference.push().setValue(course);
        Toast.makeText(Add_course.this, "Course added successfully", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Add_course.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
