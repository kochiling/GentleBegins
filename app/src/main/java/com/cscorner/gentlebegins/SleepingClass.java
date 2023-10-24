package com.cscorner.gentlebegins;

public class SleepingClass {

    private String TimeStart;
    private String TimeEnd;
    private String Duration;
    private String SleepMode;

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
        return TimeStart;
    }

    public String getTimeEnd() {
        return TimeEnd;
    }

    public String getDuration() {
        return Duration;
    }

    public String getSleepMode() {
        return SleepMode;
    }

    public SleepingClass(String timeStart, String timeEnd, String duration, String sleepMode) {
        TimeStart = timeStart;
        TimeEnd = timeEnd;
        Duration = duration;
        SleepMode = sleepMode;
    }
}
