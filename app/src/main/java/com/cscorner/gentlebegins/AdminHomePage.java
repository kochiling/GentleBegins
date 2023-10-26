package com.cscorner.gentlebegins;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminHomePage extends AppCompatActivity {
    ImageView adminlogout;
    TextView usersdetails;
    TextView additionaltips;
    AdminViewUserMyAdapter viewUserAdapter;

    RecyclerView viewUserrecyclerView;

    List<AdminViewUserDataClass> viewUserdataList;

    ValueEventListener ViewUsereventListener;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        adminlogout = findViewById(R.id.logoutButton);
        usersdetails = findViewById(R.id.usersdetails);
        additionaltips = findViewById(R.id.additionaltips);
        viewUserrecyclerView = findViewById(R.id.adminviewusersRecycleView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(AdminHomePage.this, 1);
        viewUserrecyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(AdminHomePage.this);
        builder.setCancelable(false);
        builder.setView(R.layout.manage_additional_tips_progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        viewUserdataList = new ArrayList<>();

        viewUserAdapter = new AdminViewUserMyAdapter(AdminHomePage.this, viewUserdataList);
        viewUserrecyclerView.setAdapter(viewUserAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        dialog.show();
        ViewUsereventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                viewUserdataList.clear();
                for (DataSnapshot userSnapshot: snapshot.getChildren()) {
                    // Change the key to match your data structure
                    String userId = userSnapshot.getKey();
                    DataSnapshot personalInfoSnapshot = userSnapshot.child("Personal Information");

                    if (personalInfoSnapshot.exists()) {
                        AdminViewUserDataClass dataClass = personalInfoSnapshot.getValue(AdminViewUserDataClass.class);

                        if (dataClass != null) {
                            dataClass.setKey(userId); // Set the user ID as the key

                            viewUserdataList.add(dataClass);
                        }
                    }
                }
                viewUserAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        adminlogout.setOnClickListener(view -> {
            Intent intent = new Intent(AdminHomePage.this, Login.class);
            startActivity(intent);
        });

        usersdetails.setOnClickListener(view -> {
            // You might want to refresh the user list here if needed
            viewUserdataList.clear();
            viewUserAdapter.notifyDataSetChanged();
        });

        additionaltips.setOnClickListener(view -> {
            Intent intent = new Intent(AdminHomePage.this, AdminAddTips.class);
            startActivity(intent);
        });
    }
}
