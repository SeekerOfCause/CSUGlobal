package com.csc475.myapplication.ui.home;

import androidx.annotation.NonNull;

public class TestFile {

    private String name;
    private String type;
    private String size;
    private boolean backedUp;

    // Constructor
    public TestFile(String name, String type, String size, boolean backedUp) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.backedUp = backedUp;
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

    public String getBackupStatus() { return "" + this.backedUp; }

    public boolean getBackedUp() { return this.backedUp; }

    @NonNull
    @Override
    public String toString() {
        return "Name: " + this.name + " | Type: " + this.type + " | Size: " + this.size;
    }
}