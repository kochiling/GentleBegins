package com.cscorner.gentlebegins;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Add_reminder extends AppCompatActivity {

    EditText reminder_title;
    Button reminder_date;
    Button reminder_time;
    Button reminder_save;
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth dbAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.add_reminder);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
        // Enable the Up button (back button)
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        reminder_time = findViewById(R.id.reminder_time);
        reminder_date = findViewById(R.id.reminder_date);
        reminder_title = findViewById(R.id.addTitle);
        reminder_save = findViewById(R.id.reminderSave);

        reminder_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDatePicker();
            }
        });

        reminder_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showTimePicker();
            }
        });

        reminder_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = reminder_date.getText().toString();
                String time = reminder_time.getText().toString();
                String title = reminder_title.getText().toString();

                String user_id = Objects.requireNonNull(dbAuth.getCurrentUser()).getUid();
                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users")
                        .child(user_id).child("Notifications").child("Time Stamp");

                Map<String, Object> newPost = new HashMap<>();
                newPost.put("Title",title);
                newPost.put("Date",date);
                newPost.put("Time",time);

                current_user_db.child(date).child(time).setValue(newPost);

                String message = "Saved";
                Toast.makeText(Add_reminder.this, message, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Add_reminder.this, Reminder_main.class);
                startActivity(intent);

            }
        });
    }


    private void showDatePicker() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Update the date button with the selected date
                        reminder_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, dayOfMonth);

        datePickerDialog.show();
    }

    // Function to show the time picker dialog
    private void showTimePicker() {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Format the time as "9:00" if the minute is less than 10
                        String formattedTime = (minute < 10) ?
                                hourOfDay + ":0" + minute :
                                hourOfDay + ":" + minute;

                        // Update the time button with the selected time
                        reminder_time.setText(formattedTime);
                    }
                }, hour, minute, false);

        timePickerDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {// Handle the Up button click (e.g., navigate back)
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}