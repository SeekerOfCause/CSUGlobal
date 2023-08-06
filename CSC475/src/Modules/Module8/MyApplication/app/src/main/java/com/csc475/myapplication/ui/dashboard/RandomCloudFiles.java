package com.csc475.myapplication.ui.dashboard;

import java.util.ArrayList;
import java.util.Random;

public class RandomCloudFiles extends ArrayList<CloudFile> {

    private ArrayList<CloudFile> files;

    public RandomCloudFiles() {
        this.files = generateRandomCloudFiles();
    }

    private CloudFile randomCloudFile() {
        // Array of possible file names
        String[] fileNames = {"Archimedes", "Bohr", "Curie", "Darwin", "Einstein", "Feynman", "Galileo", "Hawking", "Isaac", "Joule", "Kepler", "Lovelace", "Maxwell", "Newton", "Oppenheimer", "Pascal", "Quantum", "Rutherford", "Schrodinger", "Tesla", "Uranus", "Volta", "Watson", "Xenon", "Young", "Zeno"};

        // Array of possible file types
        String[] fileTypes = {"txt", "jpg", "png", "pdf", "docx", "pptx", "xlsx", "zip"};

        // Random number generator
        Random random = new Random();

        // Generate a random file name, type, and size
        String fileName = fileNames[random.nextInt(fileNames.length)];
        String fileType = fileTypes[random.nextInt(fileTypes.length)];
        String fileSize = (random.nextInt(991) + 10) + " KB";

        // Return a new CloudFile with these attributes
        return new CloudFile(fileName, fileType, fileSize);
    }

    private ArrayList<CloudFile> generateRandomCloudFiles() {
        ArrayList<CloudFile> cloudFilesArray = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            cloudFilesArray.add(randomCloudFile());
        }
        return cloudFilesArray;
    }

    @Override
    public int size() {
        return files.size();
    }

    @Override
    public CloudFile get(int index) {
        return files.get(index);
    }

    @Override
    public String toString() {
        StringBuilder files = new StringBuilder();

        for (int i = 0; i < this.size(); i++) {
            files.append("Name: ").append(this.get(i).getName())
                    .append(" | Type: ").append(this.get(i).getType())
                    .append(" | Size: ").append(this.get(i).getSize()).append("\n");
        }

        return files.toString();
    }
}
