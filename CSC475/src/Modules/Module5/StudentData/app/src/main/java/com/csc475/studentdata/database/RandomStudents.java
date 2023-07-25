package com.csc475.studentdata.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Random;

public class RandomStudents {
    ArrayList<Student> studentList = new ArrayList<>();

    public RandomStudents(Context context) {

        StudentDataContract.StudentDataHelper dbHelper = new StudentDataContract.StudentDataHelper(context);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("StudentData", null, null);


        // Generate 10 random students and add them to the list
        for (int i = 1; i <= 10; i++) {
            String firstName = generateRandomFirstName();
            String lastName = generateRandomLastName();
            double grade = generateRandomGrade();
            int id = i; // Assuming you want to assign ID starting from 1 and incrementing

            Student student = new Student(firstName, lastName, grade, id);

            dbHelper.write(student);
            studentList.add(student);
        }
    }

    // Helper methods to generate random names and grades
    private static String generateRandomFirstName() {
        String[] names = {"John", "Jane", "Mike", "Emily", "Alex", "Sarah"};
        Random random = new Random();
        int index = random.nextInt(names.length);
        return names[index];
    }

    private static String generateRandomLastName() {
        String[] names = {"Smith", "Rodriguez", "Johnson", "Brown", "Garcia"};
        Random random = new Random();
        int index = new Random().nextInt(names.length);
        return names[index];
    }

    private static double generateRandomGrade() {
        String randomGrade;
        Random random = new Random();
        int grade = random.nextInt(60) + 40;
        int decimal = random.nextInt(89) + 11;
        randomGrade = "" + grade + "." + decimal;// Generating grade between 60 and 100
        double finalGrade = Double.valueOf(randomGrade);
        return (double) finalGrade;
    }

    public ArrayList<Student> getStudents() {
        return this.studentList;
    }
}

