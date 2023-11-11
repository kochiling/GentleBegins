package com.cscorner.gentlebegins;

public class MedicineClass {

    private String medicineType;
    private String medSymptoms;
    private String medAmount;
    private String medDate;
    private String medTime;
    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public MedicineClass() {
    }

    public String getMedSymptoms() {
        return medSymptoms;
    }

    public String getMedicineType() {
        return medicineType;
    }

    public String getMedAmount() {
        return medAmount;
    }

    public String getMedDate() {
        return medDate;
    }

    public String getMedTime() {
        return medTime;
    }



    public MedicineClass(String medSymptoms ,String medicineType,  String medAmount, String medDate, String medTime) {
        this.medSymptoms = medSymptoms;
        this.medicineType = medicineType;
        this.medAmount = medAmount;
        this.medDate = medDate;
        this.medTime = medTime;
    }
}
