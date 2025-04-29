package com.example.homerental;

public class PropertyCategory {
    private String name;
    private int iconResourceId;
    
    public PropertyCategory(String name, int iconResourceId) {
        this.name = name;
        this.iconResourceId = iconResourceId;
    }
    
    public String getName() {
        return name;
    }
    
    public int getIconResourceId() {
        return iconResourceId;
    }
}