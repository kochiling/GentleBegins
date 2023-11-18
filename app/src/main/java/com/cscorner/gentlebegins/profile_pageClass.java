package com.cscorner.gentlebegins;

import com.google.firebase.database.PropertyName;

public class profile_pageClass {
    private String dataParentname;
    private String dataBabyname;
    private String dataBabybirthday;
    private String dataBabyGender;
    private String dataRelationship;

    // Empty constructor required by Firebase
    public profile_pageClass() {
    }

    public profile_pageClass(String dataParentname, String dataBabyname, String dataBabybirthday, String dataBabyGender, String dataRelationship) {
        this.dataParentname = dataParentname;
        this.dataBabyname = dataBabyname;
        this.dataBabybirthday = dataBabybirthday;
        this.dataBabyGender = dataBabyGender;
        this.dataRelationship = dataRelationship;
    }

    // Getter methods with annotations

    @PropertyName("dataParentname")
    public String getParentName() {
        return dataParentname;
    }

    @PropertyName("dataBabyname")
    public String getBabyname() {
        return dataBabyname;
    }

    @PropertyName("dataBabybirthday")
    public String getBabybirthday() {
        return dataBabybirthday;
    }

    @PropertyName("dataBabygender")
    public String getBabyGender() {
        return dataBabyGender;
    }

    @PropertyName("dataRelationship")
    public String getRelationship() {
        return dataRelationship;
    }
}
