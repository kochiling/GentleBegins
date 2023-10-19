package com.cscorner.gentlebegins;

import com.google.firebase.database.Exclude;
public class ReminderModel {
    private String Title;
    private String Date;
    private String Time;


    private String key;



    public ReminderModel(String Title, String Date, String Time, long Millis, Boolean OnOff) {
        this.Title = Title;
        this.Date = Date;
        this.Time = Time;
    }

    public String getTime() {
        return Time;
    }

    public String getTitle() {
        return Title;
    }

    public String getDate() {
        return Date;
    }

    public void setTime(String time) {
        this.Time = time;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setDate(String Date) {
        this.Date= Date;
    }


}

