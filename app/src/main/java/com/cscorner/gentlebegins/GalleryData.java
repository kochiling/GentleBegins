package com.cscorner.gentlebegins;

public class GalleryData {
    private String imageURL;
    private String caption;

    // Required no-argument constructor for Firebase
    public GalleryData() {
        // Default constructor required for Firebase deserialization
    }

    public GalleryData(String imageURL, String caption) {
        this.imageURL = imageURL;
        this.caption = caption;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getCaption() {
        return caption;
    }
}



