package com.example.studentmanagementsystem;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button btnLogin;
    DatabaseReference databaseReference;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.txtusername);
        editTextPassword = findViewById(R.id.txtpassword);
        btnLogin = findViewById(R.id.btnlogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Retrieve username and password
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                // Check selected user type

                if(username.equalsIgnoreCase("ADMIN") && password.equals("1234")) {
                        // Redirect to Admin Dashboard (MainActivity for example)
                        Intent adminIntent = new Intent(login.this, MainActivity.class);
                        login.this.startActivity(adminIntent);
                        finish();  // Finish the login activity to prevent going back to it
                    }
                else {isValidCredentials(username,password);}

            }
        });
    }

    private void isValidCredentials(String username1, String password1) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Student");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean credentialsValid = false;

                for (DataSnapshot classSnapshot : dataSnapshot.getChildren()) {
                    String usename2 = classSnapshot.child("username").getValue(String.class);
                    String password2 = classSnapshot.child("password").getValue(String.class);

                    if (usename2.equalsIgnoreCase(username1) &&
                            password2.equals(password1)) {

                        id = classSnapshot.child("id").getValue(String.class);
                        credentialsValid = true;
                        break;
                    }
                }

                if (credentialsValid) {
                    Intent intent = new Intent(login.this, student_dash.class);
                    intent.putExtra("StudentID", id);
                    login.this.startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(login.this, "Invalid credentials", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(login.this, "Failed to fetch data from Firebase", Toast.LENGTH_LONG).show();
            }
        });
    }

}
