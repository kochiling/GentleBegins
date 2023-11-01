package com.cscorner.gentlebegins;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import androidx.appcompat.widget.SearchView;

public class SummarySleep extends AppCompatActivity {

    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    RecyclerView recyclerView;
    List<SleepingClass> sleepList;
    SleepAdapter adapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary_sleep);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Sleeping History ");

        FirebaseAuth dbAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(SummarySleep.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        AlertDialog.Builder builder = new AlertDialog.Builder(SummarySleep.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        sleepList = new ArrayList<>();
        adapter = new SleepAdapter(SummarySleep.this, sleepList);
        recyclerView.setAdapter(adapter);

        String user_id = Objects.requireNonNull(dbAuth.getCurrentUser()).getUid();

        databaseReference =  FirebaseDatabase.getInstance().getReference("Users").child(user_id).child("Sleeping Record");
        dialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sleepList.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    SleepingClass sleepClass = itemSnapshot.getValue(SleepingClass.class);
                    assert sleepClass != null;
                    sleepClass.setKey(itemSnapshot.getKey());
                    sleepList.add(sleepClass);
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

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

    }

    public void searchList(String text) {
        ArrayList<SleepingClass> searchList = new ArrayList<>();
        for (SleepingClass sleepClass : sleepList) {
            if (sleepClass.getSleepMode().toLowerCase().contains(text.toLowerCase()) ||
                    sleepClass.getDuration().toLowerCase().contains(text.toLowerCase()) ||
                    sleepClass.getTimeStart().toLowerCase().contains(text.toLowerCase()) ||
                    sleepClass.getTimeEnd().toLowerCase().contains(text.toLowerCase())) {
                searchList.add(sleepClass);
            }
        }
        adapter.searchDataList(searchList);
    }
}