package com.cscorner.gentlebegins;

public class AddTips_DataClass {
    private String dataTitle;
    private String dataLink;
    private String dataDesc;
    private String dataImage;
    private String key;

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getDataTitle() {
        return dataTitle;
    }

    public String getDataLink() {
        return dataLink;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public String getDataImage() {
        return dataImage;
    }

    public AddTips_DataClass(String dataTitle, String dataLink, String dataDesc, String dataImage) {
        this.dataTitle = dataTitle;
        this.dataLink = dataLink;
        this.dataDesc = dataDesc;
        this.dataImage = dataImage;
    }
    public AddTips_DataClass(){

    }

}
