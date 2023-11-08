package com.cscorner.gentlebegins;
public class MilkFeedingClass  {

    private String milkDate;
    private String milkTime;
    private String milkType;
    private String milkAmount;
    private String milkUnit;
    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getMilkType() {
        return milkType;
    }

    public String getMilkAmount() {
        return milkAmount;
    }

    public String getMilkUnit() {
        return milkUnit;
    }

    public String getMilkDate() {
        return milkDate;
    }

    public String getMilkTime() {
        return milkTime;
    }


    public MilkFeedingClass() {
    }

    public MilkFeedingClass( String milkType, String milkAmount, String milkUnit,String milkDate, String milkTime) {
        this.milkType = milkType;
        this.milkAmount = milkAmount;
        this.milkUnit = milkUnit;
        this.milkDate = milkDate;
        this.milkTime = milkTime;

    }
}
