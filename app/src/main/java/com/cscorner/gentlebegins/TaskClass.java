package com.cscorner.gentlebegins;

public class TaskClass {

   private String taskTitle;
   private String taskDesc;
   private String taskDT;
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

    public TaskClass(String taskTitle, String taskDesc, String taskDT) {
        this.taskTitle = taskTitle;
        this.taskDesc = taskDesc;
        this.taskDT = taskDT;
    }

    public TaskClass() {
    }
}
