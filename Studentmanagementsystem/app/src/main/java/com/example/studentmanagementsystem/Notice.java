package com.example.studentmanagementsystem;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;

public class Notice extends AppCompatActivity {

    EditText notice;
    Button btnaddnotice;
    LocalDate date;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        notice = findViewById(R.id.editTextTextMultiLine);
        btnaddnotice = findViewById(R.id.button5);

        btnaddnotice.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                if(!TextUtils.isEmpty(notice.getText())) {

                    noticemodel note = new noticemodel(date.now().toString(), notice.getText().toString());
                    databaseReference = FirebaseDatabase.getInstance().getReference("Notice");
                    databaseReference.push().setValue(note);
                    Toast.makeText( Notice.this, "Done !!!", LENGTH_SHORT).show();
                    Intent intent = new Intent(Notice.this, MainActivity.class);
                    Notice.this.startActivity(intent);

                }
                else {
                    Toast.makeText( Notice.this, "Enter Notice !!", LENGTH_SHORT).show();
                }
            }
        });

    }
}