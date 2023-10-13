package com.cscorner.gentlebegins;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Objects;
import java.util.HashMap;
import java.util.Map;


public class Diaper_Record extends AppCompatActivity {

    Button diaperEditDate;
    Button diaperEditTime;
    RadioButton radioButton1;
    RadioButton radioButton2;
    EditText diaperEditNotes;
    Button diaperSaveButton;

    // Calendar instance to manage date and time
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth dbAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.diaper_record);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Diaper Record");
        // Enable the Up button (back button)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize your UI elements by finding their IDs
        diaperEditDate = findViewById(R.id.diaperEdit_date);
        diaperEditTime = findViewById(R.id.diaperEdit_time);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        diaperEditNotes = findViewById(R.id.diaperEdit_Notes);
        diaperSaveButton = findViewById(R.id.diaper_save);

        diaperEditDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDatePicker();
            }
        });

        diaperEditTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showTimePicker();
            }
        });

        // Set the date and time buttons to the current date and time
        updateDateTimeButtons();

        // Set a click listener for the "Save" button
        diaperSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the user input and perform data processing here
                String date = diaperEditDate.getText().toString();
                String time = diaperEditTime.getText().toString();
                String status;
                if (radioButton1.isChecked()) {
                    status = radioButton1.getText().toString();
                } else if (radioButton2.isChecked()) {
                    status = radioButton2.getText().toString();
                } else {
                    status = "No status selected";
                }
                String notes = diaperEditNotes.getText().toString();

                String user_id = Objects.requireNonNull(dbAuth.getCurrentUser()).getUid();
                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users")
                        .child(user_id).child("Diaper").child("Time Stamp");

                Map<String, Object> newPost = new HashMap<>();
                newPost.put("Diaper_status",status);
                newPost.put("Diaper_notes", notes);
                newPost.put("Date",date);
                newPost.put("Time",time);

                current_user_db.child(date).child(time).setValue(newPost);


                // For demonstration, show a Toast message with the collected data
                String message = "Date: " + date + "\nTime: " + time + "\nStatus: " + status + "\nNotes: " + notes;
                Toast.makeText(Diaper_Record.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    // Function to show the date picker dialog
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
                        diaperEditDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
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
                        diaperEditTime.setText(formattedTime);
                    }
                }, hour, minute, false);

        timePickerDialog.show();
    }

    // Override onOptionsItemSelected to handle the Up button click
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {// Handle the Up button click (e.g., navigate back)
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("SetTextI18n")
    private void updateDateTimeButtons() {
        // Get the current date and time
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Months are zero-based
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Update the date button with the current date
        diaperEditDate.setText(dayOfMonth + "/" + month + "/" + year);

        // Update the time button with the current time
        diaperEditTime.setText(hour + ":" + minute);
    }
}




