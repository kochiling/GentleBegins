package com.cscorner.gentlebegins;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
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

public class AdminAddTips extends AppCompatActivity {
    ImageView adminlogout;
    ImageView addtips;
    TextView usersdetails;
    TextView additionaltips;
    RecyclerView recyclerView;
    List<AddTips_DataClass> dataList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    AddTipsMyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_tips);

        adminlogout = findViewById(R.id.logoutButton);
        addtips = findViewById(R.id.ADDadditionaltips);
        usersdetails = findViewById(R.id.usersdetails);
        additionaltips = findViewById(R.id.additionaltips);
        recyclerView = findViewById(R.id.addtipsRecycleView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(AdminAddTips.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(AdminAddTips.this);
        builder.setCancelable(false);
        builder.setView(R.layout.manage_additional_tips_progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();

        adapter = new AddTipsMyAdapter(AdminAddTips.this, dataList);
        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Additional Tips");
        dialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    AddTips_DataClass dataClass = itemSnapshot.getValue(AddTips_DataClass.class);

                    dataClass.setKey(itemSnapshot.getKey());

                    dataList.add(dataClass);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                dialog.dismiss();
            }
        });

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