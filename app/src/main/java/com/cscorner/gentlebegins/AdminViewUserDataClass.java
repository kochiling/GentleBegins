package com.cscorner.gentlebegins;

public class AdminViewUserDataClass {
    private String dataParentname;
    private String dataRelationship;
    private String dataBabyname;
    private String dataBabygender;
    private String dataBabybirthday;
    private String key;

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getdataParentname() { return dataParentname; }

    public String getDataRelationship() {
        return dataRelationship;
    }

    public String getDataBabyname() {
        return dataBabyname;
    }

    public String getDataBabygender() {
        return dataBabygender;
    }

    public String getDataBabybirthday() {
        return dataBabybirthday;
    }

    public AdminViewUserDataClass(String dataParentname, String dataRelationship, String dataBabyname, String dataBabygender, String dataBabybirthday) {
        this.dataParentname = dataParentname;
        this.dataRelationship = dataRelationship;
        this.dataBabyname = dataBabyname;
        this.dataBabygender = dataBabygender;
        this.dataBabybirthday = dataBabybirthday;
    }
    public AdminViewUserDataClass(){

    }

}