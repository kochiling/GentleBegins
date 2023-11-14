package com.cscorner.gentlebegins;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MilkFeeding_record extends AppCompatActivity {

    Button milkEditDate;
    Button milkEditTime;
    Spinner MilkAmount_spinner;
    Spinner MilkType_spinner;
    EditText milk_Amount;
    Button milkSaveButton;
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.milk_feeding_record);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Milk Feeding Record");
        // Enable the Up button (back button)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        milkEditDate = findViewById(R.id.milkEdit_date);
        milkEditTime = findViewById(R.id.milkEdit_time);
        MilkType_spinner=findViewById(R.id.MilkType_spinner);
        MilkAmount_spinner=findViewById(R.id.MilkAmount_spinner);
        milk_Amount = findViewById(R.id.editTextAmount);
        milkSaveButton = findViewById(R.id.milkfeed_save);

        milkEditDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDatePicker();
            }
        });

        milkEditTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showTimePicker();
            }
        });

        updateDateTimeButtons();

        //Milk Type
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.milk_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MilkType_spinner.setAdapter(adapter);

        //Milk Amount
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.milk_amount, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MilkAmount_spinner.setAdapter(adapter1);

        milkSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();}

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
                        milkEditDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
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
                        milkEditTime.setText(formattedTime);
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
        milkEditDate.setText(dayOfMonth + "/" + month + "/" + year);

        // Update the time button with the current time
        milkEditTime.setText(hour + ":" + minute);
    }

    public void saveData(){
        String type = MilkType_spinner.getSelectedItem().toString();
        String unit = MilkAmount_spinner.getSelectedItem().toString();
        String amount = milk_Amount.getText().toString();
        String date = milkEditDate.getText().toString();
        String time = milkEditTime.getText().toString();

        FirebaseAuth dbAuth = FirebaseAuth.getInstance();


        MilkFeedingClass milkFeedingClass= new MilkFeedingClass(type,amount,unit,date,time);
        String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        String user_id = Objects.requireNonNull(dbAuth.getCurrentUser()).getUid();

        FirebaseDatabase.getInstance().getReference("Users").child(user_id).child("Milk Feeding Record").child(currentDate)
                .setValue(milkFeedingClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override

                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MilkFeeding_record.this, "Saved", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MilkFeeding_record.this, Objects.requireNonNull(e.getMessage()), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
