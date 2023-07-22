package com.example.trafficsigntest.components;



import com.example.trafficsigntest.R;

import java.util.ArrayList;
import java.util.Random;

public class Question {

    private Integer sign;

    private Integer[] answers;

    private int questionNumber;

    private int correctAnswer;

    private final ArrayList<Integer[]> answerList = new ArrayList<>();


    public Question(int q) {


        answerList.add(new Integer[]{R.raw.hov_lane_ahead_2_plus, R.string.answer_hov_2_plus});
        answerList.add(new Integer[]{R.raw.keep_right_divider, R.string.answer_keep_right_divider});
        answerList.add(new Integer[]{R.raw.left_lane_turn_only_right_turn_option, R.string.left_lane_turn_only_plus});
        answerList.add(new Integer[]{R.raw.no_left_or_u_turns, R.string.no_left_or_u_turn});
        answerList.add(new Integer[]{R.raw.no_left_turn, R.string.no_left_turn});
        answerList.add(new Integer[]{R.raw.no_parking, R.string.no_parking});
        answerList.add(new Integer[]{R.raw.one_way, R.string.one_way});
        answerList.add(new Integer[]{R.raw.stop, R.string.answer_stop_sign});
        answerList.add(new Integer[]{R.raw.stop_sign_ahead, R.string.stop_sign_ahead});

        buildQuestion(q);


    }

    private void buildQuestion(int q) {
        //Array of questions
        Integer[] questions = new Integer[4];

        //random int decides what radio button index the correct answer takes
        int correct = new Random().nextInt(4);
        System.out.println("Built: " + correct);

        //creates decoys for all other indexes of radio buttons
        Integer[] decoys = setDecoys(q);

        //sets answers into question array in order
        int j = 0;
        for (int i = 0; i < 4; i++) {

            if (i == correct) {
                questions[i] = setAnswer(q);
            }
            else {
                questions[i] = decoys[j];
                j++;
            }
        }

        //Set image for question q
        this.sign = setImage(q);

        //Set answers from here
        this.answers = questions;

        //Set correctAnswer as int representing index of correct answer
        this.correctAnswer = correct;

    }

    private Integer setImage(int q) {
        //Integer representing the image view
        Integer curImageView;

        //Set to Integer image corresponding to question at index q in answerList
        curImageView = answerList.get(q)[0];


        return curImageView;

    }

    private Integer setAnswer(int q) {
        //Integer representing correct answer
        Integer curAnswer;

        //Set to Integer answer corresponding to question at index q in answerList
        curAnswer = answerList.get(q)[1];

        return curAnswer;

    }

    private Integer[] setDecoys(int q) {
        //Array of Integers representing decoy answers from answerList
        Integer[] currDecoys = new Integer[3];
        int[] array = new int[3];

        //For loop to set decoys into currDecoys
        for (int i = 0; i <= 2; i++) {
            boolean cleared = true;
            //Random int from 0 to size of answerList - 1
            int randomIndex = new Random().nextInt(answerList.size());
            //If random number is the answer to the current question pick a different one, else add decoy answer
            if (randomIndex != q) {
                for (int j = 0; j < currDecoys.length; j++) {
                    if (array[j] == randomIndex) {
                        cleared = false;
                        break;
                    }
                }
                if (cleared) {
                Integer decoy = answerList.get(randomIndex)[1];
                currDecoys[i] = decoy;
                array[i] = randomIndex;
                }
                else {
                    i--;
                }
            } else {
                i--;
            }
        }
        return currDecoys;
    }

    public void setQuestionNumber(int i) {
        this.questionNumber = i;
    }

    public int getQuestionNumber() {
        return this.questionNumber;
    }

    public Integer getImage() {
        //Returns current questions image
        return this.sign;
    }

    public Integer getAnswer(int q) {
        //Return array of 4 answers for the question
        Integer[] answers = this.answers;
        return answers[q];
    }

    public int getCorrectAnswer() {
        //Return index of correct answers radial button
        return this.correctAnswer;
    }



}
