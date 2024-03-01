package com.example.studentmanagementsystem;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class update extends AppCompatActivity {

    private String idText;
    private EditText nameEditText;
    private EditText ageEditText;
    private EditText schoolEditText;
    private EditText contactNumberEditText;
    private EditText addressEditText;
    private String courseEditText;
    private EditText Username;
    private EditText Password;
    DatabaseReference databaseReference;
    private Button searchButton, updateButton ;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        idText = getIntent().getStringExtra("StudentID");
        nameEditText = findViewById(R.id.txtname2);
        ageEditText = findViewById(R.id.txtage2);
        schoolEditText = findViewById(R.id.txtmultischool2);
        contactNumberEditText = findViewById(R.id.txtcontact2);
        addressEditText = findViewById(R.id.txtmultiaddress2);
        Username = findViewById(R.id.txtUpUsername);
        Password = findViewById(R.id.txtUpPassword);

        updateButton = findViewById(R.id.btnupdate);
        backButton = findViewById(R.id.imgback);

        ImageView backButton = findViewById(R.id.imgback);

        databaseReference = FirebaseDatabase.getInstance().getReference("Student");
        fetchDataFromFirebase();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    updatedatabase();
                    Toast.makeText(update.this, "Data successfully updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(update.this, MainActivity.class);
                      update.this.startActivity(intent);
                }
            });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the main activity
                Intent intent = new Intent(update.this, update_by_sub.class);
                startActivity(intent);
            }
        });
    }


    private void fetchDataFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot classSnapshot : dataSnapshot.getChildren()) {
                    String StudentID = classSnapshot.child("id").getValue(String.class);

                    if(StudentID.equals(idText.toString())){

                        courseEditText = classSnapshot.child("course").getValue(String.class);
                        nameEditText.setText(classSnapshot.child("name").getValue(String.class));
                        ageEditText.setText(classSnapshot.child("age").getValue(String.class));
                        schoolEditText.setText(classSnapshot.child("school").getValue(String.class));
                        contactNumberEditText.setText(classSnapshot.child("telephone").getValue(String.class));
                        addressEditText.setText(classSnapshot.child("address").getValue(String.class));
                        Username.setText(classSnapshot.child("username").getValue(String.class));
                        Password.setText(classSnapshot.child("password").getValue(String.class));

                }

            }

        }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(update.this, "Failed to fetch data from Firebase", LENGTH_SHORT).show();
            };
    });

};
    private void updatedatabase() {
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot classSnapshot : dataSnapshot.getChildren()) {
                    String StudentID = classSnapshot.child("id").getValue(String.class);

                    if(StudentID.equals(idText.toString())){

                        classSnapshot.getRef().removeValue();
                        Students student = new Students(nameEditText.getText().toString(),
                                ageEditText.getText().toString(),
                                schoolEditText.getText().toString(),
                                contactNumberEditText.getText().toString(),
                                addressEditText.getText().toString(),
                                courseEditText,
                                Username.getText().toString(),
                                Password.getText().toString());
                        student.setID(idText);
                        databaseReference.push().setValue(student);
                        break;

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(update.this, "Failed to fetch data from Firebase", LENGTH_SHORT).show();
            };
        });

    };
}
