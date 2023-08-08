package com.csc475.myapplication.ui.home;

import java.util.ArrayList;
import java.util.Random;

public class RandomFiles extends ArrayList<TestFile> {

    public RandomFiles() {
        generateRandom();
    }

    private TestFile random() {
        // Array of possible file names
        String[] fileNames = {"Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliet", "Kilo", "Lima", "Mike", "November", "Oscar", "Papa", "Quebec", "Romeo", "Sierra", "Tango", "Uniform", "Victor", "Whiskey", "X-ray", "Yankee", "Zulu"};

        // Array of possible file types
        String[] fileTypes = {"txt", "jpg", "png", "pdf", "docx", "pptx", "xlsx", "zip"};

        // Random number generator
        Random random = new Random();

        // Random file name
        String fileName = fileNames[random.nextInt(fileNames.length)];
        // Random file type
        String fileType = fileTypes[random.nextInt(fileTypes.length)];
        // Random file size between 10KB and 1000KB
        String fileSize = (random.nextInt(991) + 10) + " KB";
        boolean backedUp;
        int randomUp = random.nextInt(2);
        if (randomUp != 1) {
            backedUp = false;
        }
        else if (randomUp != 0){
            backedUp = true;
        }
        else {
            backedUp = true;
        }
        // Create new TestFile
        TestFile file = new TestFile(fileName, fileType, fileSize, backedUp);
        System.out.println(file.toString());
        return file;
    }

    private void generateRandom() {
        for (int i = 0; i <= 30; i++) {
            this.add(random());  // add directly to this object (which is an ArrayList<TestFile>)
        }
    }

    @Override
    public String toString() {
        StringBuilder files = new StringBuilder();

        for (int i = 0; i < this.size(); i++) {
            files.append("Name: ").append(this.get(i).getName()).append(" | Type: ").append(this.get(i).getType()).append(" | Size: ").append(this.get(i).getSize()).append("\n");
        }

        return files.toString();
    }
}
