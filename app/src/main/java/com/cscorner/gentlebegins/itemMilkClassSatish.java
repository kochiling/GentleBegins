package com.cscorner.gentlebegins;

public class itemMilkClassSatish  {

    private String itemMilkAmount;
    private String itemMilkDate;
    private String itemMilkTime;
    private String itemMilkType;
    private String itemMilkUnit;
    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getItemMilkAmount() {

        return itemMilkAmount;
    }

    public String getItemMilkDate() {
        return itemMilkDate;
    }

    public String getItemMilkTime() {
        return itemMilkTime;
    }

    public String getItemMilkType() {
        return itemMilkType;
    }


    public String getItemMilkUnit() {
        return itemMilkUnit;
    }



    public itemMilkClassSatish() {
    }

    public itemMilkClassSatish(String itemMilkAmount, String itemMilkDate, String itemMilkTime,String itemMilkType, String itemMilkUnit) {

        this.itemMilkAmount = itemMilkAmount;
        this.itemMilkDate = itemMilkDate;
        this.itemMilkTime = itemMilkTime;
        this.itemMilkType = itemMilkType;
        this.itemMilkUnit = itemMilkUnit;

    }
}
