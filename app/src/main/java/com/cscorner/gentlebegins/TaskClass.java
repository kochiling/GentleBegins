package com.cscorner.gentlebegins;

public class TaskClass {

    private String taskTitle;
    private String taskDesc;
    private String taskDT;
    private boolean isDone;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public String getTaskDT() {
        return taskDT;
    }

    //**
    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public TaskClass(String taskTitle, String taskDesc, String taskDT) {
        this.taskTitle = taskTitle;
        this.taskDesc = taskDesc;
        this.taskDT = taskDT;

    }
    public TaskClass() {
    }
}