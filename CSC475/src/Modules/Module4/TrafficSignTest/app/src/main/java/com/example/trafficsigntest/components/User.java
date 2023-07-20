package com.example.trafficsigntest.components;

public class User {
    String userName;
    int correctAnswers;

    public User() {
        userName = "";
        correctAnswers = 0;
    }
    public User(String user, int score) {
        this.userName = user;
        this.correctAnswers = score;
    }

    public int getScore() {
        return this.correctAnswers;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setScore(int i) {
        if (i > this.correctAnswers) {
            this.correctAnswers = i;
        }
    }

    public boolean verify() {
        if (this.userName == "") {
            return false;
        }
        else {
            return true;
        }
    }
}
