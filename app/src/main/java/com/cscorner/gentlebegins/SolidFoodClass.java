package com.cscorner.gentlebegins;

public class SolidFoodClass {
    private String FoodTime;
    private String FoodDate;
    private String FoodNotes;
    private String FoodType;

    public String getFoodTime() {
        return FoodTime;
    }

    public String getFoodDate() {
        return FoodDate;
    }

    public String getFoodNotes() {
        return FoodNotes;
    }

    public String getFoodType() {
        return FoodType;
    }

    public SolidFoodClass() {

    }

    public SolidFoodClass(String foodTime, String foodDate, String foodNotes, String foodType) {
        FoodTime = foodTime;
        FoodDate = foodDate;
        FoodNotes = foodNotes;
        FoodType = foodType;
    }
}
