package com.cscorner.gentlebegins;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {

    ImageButton todolist;
    TextView recordroutines;
    TextView addgallery;
    TextView profilepgae;
    RecyclerView recyclerView;
    List<AddTips_DataClass> dataList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    UserViewTipsMyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        todolist = findViewById(R.id.alarmIcon);
        recordroutines = findViewById(R.id.UserRecord);
        addgallery = findViewById(R.id.addgallery);
        profilepgae = findViewById(R.id.UserProfile);
        recyclerView = findViewById(R.id.UseraddtipsRecycleView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(HomePage.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(HomePage.this);
        builder.setCancelable(false);
        builder.setView(R.layout.manage_additional_tips_progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();

        adapter = new UserViewTipsMyAdapter(HomePage.this, dataList);
        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Additional Tips");
        dialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
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

        todolist.setOnClickListener(view -> {
            Intent intent = new Intent(HomePage.this, Main_Task.class);
            startActivity(intent);
        });

        recordroutines.setOnClickListener(view -> {
            Intent intent = new Intent(HomePage.this, Record_routines.class);
            startActivity(intent);
        });

        addgallery.setOnClickListener(view -> {
            Intent intent = new Intent(HomePage.this, HomePage.class);
            startActivity(intent);
        });

        profilepgae.setOnClickListener(view -> {
            Intent intent = new Intent(HomePage.this, HomePage.class);
            startActivity(intent);

        });
    }
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut(); // Sign out the user

        // Redirect to a login activity
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish(); // Close the current activity
    }
}