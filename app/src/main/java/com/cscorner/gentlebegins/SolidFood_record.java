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
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;
import java.util.Objects;

public class SolidFood_record extends AppCompatActivity {

    Button foodEditDate;
    Button foodEditTime;
    EditText foodNotes;
    EditText foodType;
    Button foodSaveButton;
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solid_food_record);

        Toolbar toolbar3 = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar3);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Solid Food Record");
        // Enable the Up button (back button)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        foodEditDate = findViewById(R.id.foodEdit_date);
        foodEditTime = findViewById(R.id.foodEdit_time);
        foodNotes = findViewById(R.id.editTextSolidFoodNotes);
        foodType = findViewById(R.id.editTextFoodType);
        foodSaveButton = findViewById(R.id.foodfeed_save);

        foodEditDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDatePicker();
            }
        });

        foodEditTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showTimePicker();
            }
        });

        updateDateTimeButtons();

        foodSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String date = foodEditDate.getText().toString();
                String time = foodEditTime.getText().toString();
                String type = foodType.getText().toString();
                String notes = foodNotes.getText().toString();

                String message = type + notes + date + time;
                Toast.makeText(SolidFood_record.this, message, Toast.LENGTH_LONG).show();
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
                        foodEditTime.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
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
                        // Update the time button with the selected time
                        foodEditTime.setText(hourOfDay + ":" + minute);
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
        foodEditDate.setText(dayOfMonth + "/" + month + "/" + year);

        // Update the time button with the current time
        foodEditTime.setText(hour + ":" + minute);
    }
}