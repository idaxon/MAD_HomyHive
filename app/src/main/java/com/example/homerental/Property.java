package com.example.homerental;

public class Property {
    private String name;
    private String category;
    private String description;
    private int imageResourceId;
    private float rating;
    private int pricePerNight;
    private String address;
    private String price;
    private String location; // Add this field
    
    // Constructor used in ExploreFragment
    public Property(String name, String category, String description, int imageResourceId, float rating, int pricePerNight) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.imageResourceId = imageResourceId;
        this.rating = rating;
        this.pricePerNight = pricePerNight;
    }
    
    // Constructor used in HomeFragment
    public Property(String name, String address, String price, String description, int imageResourceId) {
        this.name = name;
        this.address = address;
        this.price = price;
        this.description = description;
        this.imageResourceId = imageResourceId;
        this.location = address; // Initialize location with address
    }
    
    public String getName() {
        return name;
    }
    
    public String getCategory() {
        return category;
    }
    
    public String getDescription() {
        return description;
    }
    
    public int getImageResourceId() {
        return imageResourceId;
    }
    
    public float getRating() {
        return rating;
    }
    
    public int getPricePerNight() {
        return pricePerNight;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getPrice() {
        return price;
    }
    
    // Add the missing getLocation method
    public String getLocation() {
        return location != null ? location : address; // Return location if set, otherwise return address
    }
    
    // Add setter for location if needed
    public void setLocation(String location) {
        this.location = location;
    }
}