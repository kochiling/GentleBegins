package com.cscorner.gentlebegins;
public class MilkFeedingClass {

    private String MilkDate;
    private String MilkTime;
    private String MilkType;
    private String MilkAmount;
    private String MilkUnit;
    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getMilkDate() {
        return MilkDate;
    }

    public String getMilkTime() {
        return MilkTime;
    }

    public String getMilkType() {
        return MilkType;
    }

    public String getMilkAmount() {
        return MilkAmount;
    }

    public String getMilkUnit() {
        return MilkUnit;
    }

    public MilkFeedingClass() {
    }

    public MilkFeedingClass(String milkDate, String milkTime, String milkType, String milkAmount, String milkUnit) {
        MilkDate = milkDate;
        MilkTime = milkTime;
        MilkType = milkType;
        MilkAmount = milkAmount;
        MilkUnit = milkUnit;
    }
}
