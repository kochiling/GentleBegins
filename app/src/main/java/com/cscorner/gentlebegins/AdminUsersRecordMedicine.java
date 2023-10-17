package com.cscorner.gentlebegins;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminUsersRecordMedicine extends AppCompatActivity {

    ImageView backTOauserprofilepage;
    Button adminlogout;
    Button feedingrecord;
    Button sleepingrecord;
    Button diaperrecord;
    Button medicinerecord;
    TextView usersdetails;
    TextView additionaltips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_users_record_medicine);

        backTOauserprofilepage = findViewById(R.id.backTOuserprofilepage);
        adminlogout = findViewById(R.id.logoutButton);
        feedingrecord = findViewById(R.id.adminfeeding);
        sleepingrecord = findViewById(R.id.adminsleeping);
        diaperrecord = findViewById(R.id.admindiaper);
        medicinerecord = findViewById(R.id.adminmedicine);
        usersdetails = findViewById(R.id.usersdetails);
        additionaltips = findViewById(R.id.additionaltips);

        backTOauserprofilepage.setOnClickListener(view -> {
            Intent intent = new Intent(AdminUsersRecordMedicine.this, AdminUserProfile.class);
            startActivity(intent);
        });

        adminlogout.setOnClickListener(view -> {
            Intent intent = new Intent(AdminUsersRecordMedicine.this, Login.class);
            startActivity(intent);
        });

        feedingrecord.setOnClickListener(view -> {
            Intent intent = new Intent(AdminUsersRecordMedicine.this, AdminUsersRecordFeeding.class);
            startActivity(intent);
        });

        sleepingrecord.setOnClickListener(view -> {
            Intent intent = new Intent(AdminUsersRecordMedicine.this, AdminUsersRecordSleeping.class);
            startActivity(intent);
        });

        diaperrecord.setOnClickListener(view -> {
            Intent intent = new Intent(AdminUsersRecordMedicine.this, AdminUsersRecordDiaper.class);
            startActivity(intent);
        });

        medicinerecord.setOnClickListener(view -> {
            Intent intent = new Intent(AdminUsersRecordMedicine.this, AdminUsersRecordMedicine.class);
            startActivity(intent);
        });

        usersdetails.setOnClickListener(view -> {
            Intent intent = new Intent(AdminUsersRecordMedicine.this, AdminHomePage.class);
            startActivity(intent);
        });

        additionaltips.setOnClickListener(view -> {
            Intent intent = new Intent(AdminUsersRecordMedicine.this, AdminAddTips.class);
            startActivity(intent);

        });
    }
}