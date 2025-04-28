package com.example.homerental;

public class Property {
    private String name;
    private String address;
    private String price;
    private String description;
    private int imageResourceId;
    private String location;

    public Property(String name, String address, String price, String description, int imageResourceId) {
        this.name = name;
        this.address = address;
        this.price = price;
        this.description = description;
        this.imageResourceId = imageResourceId;
        this.location = ""; // Default empty location
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}