package com.cscorner.gentlebegins;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import androidx.appcompat.widget.Toolbar;
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
import java.util.Objects;


public class SummaryFood extends AppCompatActivity {

    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    RecyclerView recyclerView;
    List<SolidFoodClass> solidfoodList;
    SolidFoodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_food);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Solid Food Feeding History ");

        FirebaseAuth dbAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(SummaryFood.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        AlertDialog.Builder builder = new AlertDialog.Builder(SummaryFood.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        solidfoodList = new ArrayList<>();
        adapter = new SolidFoodAdapter(SummaryFood.this, solidfoodList);
        recyclerView.setAdapter(adapter);

        String user_id = Objects.requireNonNull(dbAuth.getCurrentUser()).getUid();

        databaseReference =  FirebaseDatabase.getInstance().getReference("Users").child(user_id).child("Solid Food Feeding Record");
        dialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                solidfoodList.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    SolidFoodClass foodClass = itemSnapshot.getValue(SolidFoodClass.class);
                    assert foodClass != null;
                    foodClass.setKey(itemSnapshot.getKey());
                    solidfoodList.add(foodClass);
                }

                //add
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });


    }
}