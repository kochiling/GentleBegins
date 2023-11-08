package com.cscorner.gentlebegins;

public class SleepingClass {

    private String timeStart;
    private String timeEnd;
    private String duration;
    private String sleepMode;

    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public SleepingClass() {
    }

    public String getTimeStart() {
        return timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public String getDuration() {
        return duration;
    }

    public String getSleepMode() {
        return sleepMode;
    }

    public SleepingClass(String timeStart, String timeEnd, String duration, String sleepMode) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.duration = duration;
        this.sleepMode = sleepMode;
    }
}
