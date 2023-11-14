package com.cscorner.gentlebegins;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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



public class Summary_Feeding_satish extends AppCompatActivity {

    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    RecyclerView recyclerView;
    List<itemMilkClassSatish> milkList;
    itemMilkAdapterSatish adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_feeding_satish);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Milk Feeding History ");

        FirebaseAuth dbAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.recyclerView);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(Summary_Feeding_satish.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        AlertDialog.Builder builder = new AlertDialog.Builder(Summary_Feeding_satish.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        milkList = new ArrayList<>();
        adapter = new itemMilkAdapterSatish(Summary_Feeding_satish.this, milkList);
        recyclerView.setAdapter(adapter);

        String user_id = Objects.requireNonNull(dbAuth.getCurrentUser()).getUid();

        databaseReference =  FirebaseDatabase.getInstance().getReference("Users").child(user_id).child("Milk Feeding Record");
        dialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                milkList.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    itemMilkClassSatish milkClass = itemSnapshot.getValue(itemMilkClassSatish.class);
                    assert milkClass != null;
                    milkClass.setKey(itemSnapshot.getKey());
                    milkList.add(milkClass);
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