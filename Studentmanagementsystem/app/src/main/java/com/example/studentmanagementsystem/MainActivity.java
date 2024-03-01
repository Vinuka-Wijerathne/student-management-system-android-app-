package com.example.studentmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button button1, button2, button3, button4,button5;
    TextView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons
        button1 = findViewById(R.id.regbtn);
        button2 = findViewById(R.id.updatebtn);
        // Assuming noticesbtn and attenbtn are not used in this activity, comment them out
        button3 = findViewById(R.id.noticesbtn);
        button4 = findViewById(R.id.attenbtn);
        button5 = findViewById(R.id.addcoursebtn);
        logout = findViewById(R.id.textView2);


        // Set click listeners
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to RegisterActivity (replace with your actual activity name)
                Intent intent = new Intent(MainActivity.this, register.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to UpdateActivity (replace with your actual activity name)
                Intent intent = new Intent(MainActivity.this, update_by_sub.class);
                startActivity(intent);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to UpdateActivity (replace with your actual activity name)
                Intent intent = new Intent(MainActivity.this, Add_course.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to NoticesActivity (replace with your actual activity name)
                Intent intent = new Intent(MainActivity.this, Notice.class);
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to AttendanceActivity (replace with your actual activity name)
                Intent intent = new Intent(MainActivity.this, attendance.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
