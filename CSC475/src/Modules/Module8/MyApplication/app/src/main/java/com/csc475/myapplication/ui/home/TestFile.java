package com.csc475.myapplication.ui.home;

public class TestFile {

    private String name;
    private String type;
    private String size;

    // Constructor
    public TestFile(String name, String type, String size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }

    // Getters
    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public String getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + " | Type: " + this.type + " | Size: " + this.size;
    }
}