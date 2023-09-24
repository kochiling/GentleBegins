package com.cscorner.gentlebegins;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import java.util.Calendar;


public class Diaper_Record extends AppCompatActivity {

    private EditText diaperEdit_date;
    private Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diaper_record);

        diaperEdit_date = findViewById(R.id.diaperEdit_date);

        // Initialize the calendar
        calendar = Calendar.getInstance();

        // Set a click listener on the EditText to show the date picker
        diaperEdit_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Diaper_Record.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Handle the selected date here
                                String selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                                diaperEdit_date.setText(selectedDate);
                            }
                        },
                        year,
                        month,
                        day
                );
                datePickerDialog.show();
            }
        });

    }
}



