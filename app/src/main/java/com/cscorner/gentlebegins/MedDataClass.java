package com.cscorner.gentlebegins;

public class MedDataClass {

    private String medAmount;
    private String medDate;
    private String medSymptoms;
    private String medTime;
    private String medicineType;

    public String getMedAmount() {
        return medAmount;
    }

    public String getMedDate() {
        return medDate;
    }

    public String getMedSymptoms() {
        return medSymptoms;
    }

    public String getMedTime() {
        return medTime;
    }

    public String getMedicineType() {
        return medicineType;
    }

    public MedDataClass(String medAmount, String medDate, String medSymptoms, String medTime, String medicineType) {
        this.medAmount = medAmount;
        this.medDate = medDate;
        this.medSymptoms = medSymptoms;
        this.medTime = medTime;
        this.medicineType = medicineType;
    }

    // Default constructor is required for Firebase
    public MedDataClass() {
    }
}
