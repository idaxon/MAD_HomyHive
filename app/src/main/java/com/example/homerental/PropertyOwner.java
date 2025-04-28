package com.example.homerental;

public class PropertyOwner {
    private String name;
    private String lookingFor;
    private String bio;
    private int imageResourceId;
    private String location; // Add this field

    public PropertyOwner(String name, String lookingFor, String bio, int imageResourceId) {
        this.name = name;
        this.lookingFor = lookingFor;
        this.bio = bio;
        this.imageResourceId = imageResourceId;
        this.location = ""; // Default empty location
    }

    // Alternative constructor with location
    public PropertyOwner(String name, String lookingFor, String bio, int imageResourceId, String location) {
        this.name = name;
        this.lookingFor = lookingFor;
        this.bio = bio;
        this.imageResourceId = imageResourceId;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLookingFor() {
        return lookingFor;
    }

    public void setLookingFor(String lookingFor) {
        this.lookingFor = lookingFor;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    // Fix the getPropertyInfo method to return an existing field
    public String getPropertyInfo() {
        return lookingFor; // Using lookingFor as the property info
    }

    // Add these methods for location
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}