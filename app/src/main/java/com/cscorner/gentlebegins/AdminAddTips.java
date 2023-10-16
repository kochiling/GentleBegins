package com.cscorner.gentlebegins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class AdminAddTips extends AppCompatActivity {
    Button adminlogout;
    ImageButton addtips;
    TextView usersdetails;
    TextView additionaltips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_tips);

        adminlogout = findViewById(R.id.logoutButton);
        addtips = findViewById(R.id.ADDadditionaltips);
        usersdetails = findViewById(R.id.usersdetails);
        additionaltips = findViewById(R.id.additionaltips);

        adminlogout.setOnClickListener(view -> {
            Intent intent = new Intent(AdminAddTips.this, Login.class);
            startActivity(intent);
        });

        addtips.setOnClickListener(view -> {
            Intent intent = new Intent(AdminAddTips.this, ManageAdditionalTips.class);
            startActivity(intent);
        });

        usersdetails.setOnClickListener(view -> {
            Intent intent = new Intent(AdminAddTips.this, AdminHomePage.class);
            startActivity(intent);
        });

        additionaltips.setOnClickListener(view -> {
            Intent intent = new Intent(AdminAddTips.this, AdminAddTips.class);
            startActivity(intent);

        });
    }
}