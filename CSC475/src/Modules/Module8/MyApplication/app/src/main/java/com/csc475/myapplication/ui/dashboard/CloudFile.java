package com.csc475.myapplication.ui.dashboard;

public class CloudFile {
    private String name;
    private String type;
    private String size;

    public CloudFile(String name, String type, String size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }
}

