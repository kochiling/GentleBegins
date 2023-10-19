package com.cscorner.gentlebegins;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;



public class Reminder_main extends AppCompatActivity {

    FloatingActionButton add;
    RecyclerView recyclerView;
    ArrayList<ReminderModel> list;
    DatabaseReference databaseReference;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_main);

        add = findViewById(R.id.floatingActionButton);

        add.setOnClickListener(view -> {
            Intent intent = new Intent(Reminder_main.this, Add_reminder.class);
            startActivity(intent);
        });

        Toolbar toolbar = findViewById(R.id.toolbar_remind);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Reminder");
        // Enable the Up button (back button)
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);


        FirebaseAuth dbAuth = FirebaseAuth.getInstance();
        String user_id = dbAuth.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users/" + user_id + "/Notifications");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, list);


        recyclerView.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear(); // Clear the list before adding new data

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ReminderModel notifications = dataSnapshot.getValue(ReminderModel.class);
                    list.add(notifications);
                }

                if (list.isEmpty()) {
                    // The list is empty, show a message to the user
                    Toast.makeText(Reminder_main.this, "No reminders found", Toast.LENGTH_SHORT).show();
                } else {
                    // The list is not empty, update the RecyclerView
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", error.getMessage());
            }
        });
    }



    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {// Handle the Up button click (e.g., navigate back)
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}