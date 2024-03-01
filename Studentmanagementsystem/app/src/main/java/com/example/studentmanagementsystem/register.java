// RegisterActivity.java
package com.example.studentmanagementsystem;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class register extends AppCompatActivity {

    private EditText nameEditText, ageEditText, schoolEditText, contactNumberEditText, addressEditText, Usename , Password;
    private Spinner course;
    ArrayList<String> spinnerDataList;
    ArrayAdapter<String> adapter;
    private ValueEventListener listener;

    int count = 0;

    // Firebase
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Assuming you have an ImageView with ID "imageView" in your activity_register.xml layout
        ImageView imageView = findViewById(R.id.imageView);

        nameEditText = findViewById(R.id.txtname);
        ageEditText = findViewById(R.id.txtage);
        schoolEditText = findViewById(R.id.txtmultischool);
        contactNumberEditText = findViewById(R.id.txtcontact);
        addressEditText = findViewById(R.id.txtAddress2);
        course = findViewById(R.id.DDCourse);
        Usename = findViewById(R.id.txtRegUsername);
        Password  = findViewById(R.id.txtRegPassword);

        databaseReference = FirebaseDatabase.getInstance().getReference("courses");
        spinnerDataList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(register.this, android.R.layout.simple_spinner_dropdown_item, spinnerDataList);
        course.setAdapter(adapter);
        retrieveData();

        Button registerButton = findViewById(R.id.btnreg);

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("students");

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the main activity
                Intent intent = new Intent(register.this, MainActivity.class);
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered data from EditText widgets
                String name = nameEditText.getText().toString();
                String age = ageEditText.getText().toString();
                String school = schoolEditText.getText().toString();
                String contactNumber = contactNumberEditText.getText().toString();
                String address = addressEditText.getText().toString();
                String course1 = ((String) course.getSelectedItem()).toString();
                String username = Usename.getText().toString();
                String password = Password.getText().toString();

                // Validate input fields
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(age) || TextUtils.isEmpty(school)
                        || TextUtils.isEmpty(contactNumber) || TextUtils.isEmpty(address) || TextUtils.isEmpty(username) ||
                        TextUtils.isEmpty(password) || TextUtils.isEmpty(course1)) {
                    // Display an error message if any field is empty
                    Toast.makeText(register.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Create a Student object with the entered data
                    Students student = new Students(name, age, school, contactNumber, address, course1 , username , password);

                    databaseReference = FirebaseDatabase.getInstance().getReference("Student");

                    int count = fetchDataFromFirebase();

                    String Studentid = String.valueOf(count + 1);
                    student.setID(Studentid);

                    databaseReference.push().setValue(student);
                    // For demonstration purposes, display a Toast with the entered data
                    String message = "Registered Successfully !!";
                    Toast.makeText(register.this, message, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private int fetchDataFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot classSnapshot : dataSnapshot.getChildren()) {

                    count++;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return count;
    }

    public void retrieveData(){
        listener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                spinnerDataList.clear();
                for (DataSnapshot item : dataSnapshot.getChildren()){
                    spinnerDataList.add(item.child("courseName").getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
