package com.cscorner.gentlebegins;

public class DiaperClass {

    private String diaperStatus;
    private String diaperNotes;
    private String diaperDate;
    private String diaperTime;

    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getDiaperStatus() {
        return diaperStatus;
    }

    public String getDiaperNotes() {
        return diaperNotes;
    }

    public String getDiaperDate() {
        return diaperDate;
    }

    public String getDiaperTime() {
        return diaperTime;
    }

    public DiaperClass(String diaperStatus, String diaperNotes, String diaperDate, String diaperTime) {
        this.diaperStatus = diaperStatus;
        this.diaperNotes = diaperNotes;
        this.diaperDate = diaperDate;
        this.diaperTime = diaperTime;
    }

    public DiaperClass() {
    }
}
