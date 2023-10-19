package com.cscorner.gentlebegins;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminHomePage extends AppCompatActivity {
    ImageView adminlogout;
    TextView usersdetails;
    TextView additionaltips;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        adminlogout = findViewById(R.id.logoutButton);
        usersdetails = findViewById(R.id.usersdetails);
        additionaltips = findViewById(R.id.additionaltips);

        adminlogout.setOnClickListener(view -> {
            Intent intent = new Intent(AdminHomePage.this, Login.class);
            startActivity(intent);
        });

        usersdetails.setOnClickListener(view -> {
            Intent intent = new Intent(AdminHomePage.this, AdminHomePage.class);
            startActivity(intent);
        });

        additionaltips.setOnClickListener(view -> {
            Intent intent = new Intent(AdminHomePage.this, AdminAddTips.class);
            startActivity(intent);
        });

    }
}