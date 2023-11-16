package com.cscorner.gentlebegins;

public class SolidFoodClass {
    private String foodTime;
    private String foodDate;
    private String foodNotes;
    private String foodType;

    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getFoodType() {
        return foodType;
    }
    public String getFoodNotes() {
        return foodNotes;
    }
    public String getFoodDate() {
        return foodDate;
    }
    public String getFoodTime() {
        return foodTime;
    }
    public SolidFoodClass() {

    }

    public SolidFoodClass(String foodType,String foodNotes, String foodDate,String foodTime  ) {

        this.foodType = foodType;
        this.foodNotes = foodNotes;
        this.foodDate = foodDate;
        this.foodTime = foodTime;
    }
}




