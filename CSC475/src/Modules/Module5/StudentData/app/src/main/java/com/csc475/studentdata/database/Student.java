package com.csc475.studentdata.database;

public class Student {

    private String firstName;
    private String lastName;
    private double grade;
    private int id;

    public Student(String first, String last, double grade, int id){
        this.firstName = first;
        this.lastName = last;
        this.grade = grade;
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public double getGrade() {
        return this.grade;
    }

    public int getId() {
        return this.id;
    }
}
