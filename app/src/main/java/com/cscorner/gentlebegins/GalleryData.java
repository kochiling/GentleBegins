package com.cscorner.gentlebegins;

public class GalleryData {
    private String imageURL, caption, userId;

    public GalleryData() {
        // Default no-argument constructor required by Firebase Realtime Database.
    }

    public GalleryData(String imageURL, String caption, String userId) {
        this.imageURL = imageURL;
        this.caption = caption;
        this.userId = userId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}


