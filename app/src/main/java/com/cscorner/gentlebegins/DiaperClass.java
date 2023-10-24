package com.cscorner.gentlebegins;

public class DiaperClass {

    private String DiaperStatus;
    private String DiaperNotes;
    private String DiaperDate;
    private String DiaperTime;

    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getDiaperStatus() {
        return DiaperStatus;
    }

    public String getDiaperNotes() {
        return DiaperNotes;
    }

    public String getDiaperDate() {
        return DiaperDate;
    }

    public String getDiaperTime() {
        return DiaperTime;
    }

    public DiaperClass(String diaperStatus, String diaperNotes, String diaperDate, String diaperTime) {
        DiaperStatus = diaperStatus;
        DiaperNotes = diaperNotes;
        DiaperDate = diaperDate;
        DiaperTime = diaperTime;
    }

    public DiaperClass() {
    }
}
