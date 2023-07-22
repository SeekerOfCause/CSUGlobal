package com.example.trafficsigntest.components;

import java.util.ArrayList;
import java.util.Random;

public class Test {

    private final ArrayList<Question> test;

    public Test(int q) {
        ArrayList<Question> currTest = new ArrayList<>();
        //Random number to get question with
        ArrayList<Integer> array = new ArrayList<>();
        //Initialize new array to hold questions

        for (int i = 0; i < q; i++) {
            int randomIndex = new Random().nextInt(q);
            boolean cleared = true;
            for (int j = 0; j < array.size(); j++) {
                if (array.get(j) == randomIndex) {
                    cleared = false;
                }
            }
            if (cleared) {
                Question question = new Question(randomIndex);
                question.setQuestionNumber(i + 1);
                currTest.add(question);
                array.add(randomIndex);
            }
            else {
                i--;
            }

        }

        this.test =currTest;
}

    public int getTestLength() {
        return this.test.size();
    }

    public Question getQuestion() {
        return this.test.remove(0);
    }
}
