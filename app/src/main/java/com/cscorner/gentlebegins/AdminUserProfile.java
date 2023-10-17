package com.cscorner.gentlebegins;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AdminUserProfile extends AppCompatActivity {
    ImageView backTOadminhomepage;
    Button adminlogout;
    TextView userrecorddetails;
    TextView usersdetails;
    TextView additionaltips;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_profile);

        backTOadminhomepage = findViewById(R.id.backTOadminpage);
        adminlogout = findViewById(R.id.logoutButton);
        userrecorddetails = findViewById(R.id.userrecorddetails);
        usersdetails = findViewById(R.id.usersdetails);
        additionaltips = findViewById(R.id.additionaltips);


        backTOadminhomepage.setOnClickListener(view -> {
            Intent intent = new Intent(AdminUserProfile.this, AdminHomePage.class);
            startActivity(intent);
        });

        adminlogout.setOnClickListener(view -> {
            Intent intent = new Intent(AdminUserProfile.this, Login.class);
            startActivity(intent);
        });

        userrecorddetails.setOnClickListener(view -> {
            Intent intent = new Intent(AdminUserProfile.this, AdminUsersRecordFeeding.class);
            startActivity(intent);
        });

        usersdetails.setOnClickListener(view -> {
            Intent intent = new Intent(AdminUserProfile.this, AdminHomePage.class);
            startActivity(intent);
        });

        additionaltips.setOnClickListener(view -> {
            Intent intent = new Intent(AdminUserProfile.this, AdminAddTips.class);
            startActivity(intent);

        });
    }
}