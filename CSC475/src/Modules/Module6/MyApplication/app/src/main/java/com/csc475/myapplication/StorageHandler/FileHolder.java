package com.csc475.myapplication.StorageHandler;

import android.net.Uri;

import java.io.File;

public class FileHolder extends File {

    private Uri uri;
    private String name;
    private String type;
    private int size;


    public FileHolder(Uri uri, String name, String type, int size) {
        super(String.valueOf(uri));
        this.uri = uri;
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public Uri getFileUri() {
        return this.uri;
    }

    public String getFileName() {
        return this.name;
    }

    public String getFileType() {
        return this.type;
    }

    public int getFileSize() {
        return this.size;
    }
}
