package com.example.projectinfoblast;

public class Item {

    String username;
    String description;
    String status;
    int image, profile;



    public Item(String username, String description , String status, int image, int profile) {
        this.username = username;
        this.description = description;
        this.image = image;
        this.profile = profile;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
