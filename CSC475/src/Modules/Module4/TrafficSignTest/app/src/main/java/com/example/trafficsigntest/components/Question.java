package com.example.trafficsigntest.components;



import com.example.trafficsigntest.R;

import java.util.ArrayList;
import java.util.Random;

public class Question {


    private Integer sign;

    private Integer answer;

    private Integer[] decoys;

    ArrayList<Integer[]> imageCollection = new ArrayList<Integer[]>();



    public Question(int q) {


        imageCollection.add(new Integer[]{R.raw.hov_lane_ahead_2_plus, R.string.answer_hov_2_plus});
        imageCollection.add(new Integer[]{R.raw.keep_right_divider, R.string.answer_keep_right_divider});
        imageCollection.add(new Integer[]{R.raw.left_lane_turn_only_right_turn_option, R.string.left_lane_turn_only_plus});
        imageCollection.add(new Integer[]{R.raw.no_left_or_u_turns, R.string.no_left_or_u_turn});
        imageCollection.add(new Integer[]{R.raw.no_left_turn, R.string.no_left_turn});
        imageCollection.add(new Integer[]{R.raw.no_parking, R.string.no_parking});
        imageCollection.add(new Integer[]{R.raw.one_way, R.string.one_way});
        imageCollection.add(new Integer[]{R.raw.stop, R.string.answer_stop_sign});
        imageCollection.add(new Integer[]{R.raw.stop_sign_ahead, R.string.stop_sign_ahead});

        this.answer = setAnswer(q);

        this.decoys = setDecoys(q);

        this.sign = setImage(q);
    }

    private Integer setImage(int q) {

        Integer curImageView;


        curImageView = imageCollection.get(q)[0];


        return curImageView;

    }

    private Integer setAnswer(int q) {
        Integer curAnswer;
        curAnswer = imageCollection.get(q)[1];

        return curAnswer;

    }

    private Integer[] setDecoys(int q) {
        Integer[] currDecoys = new Integer[3];

        for (int i = 0; i <= 2; i++) {
            int randomIndex = new Random().nextInt(imageCollection.size() - 1);
            if (randomIndex != q) {
                Integer decoy = imageCollection.get(randomIndex)[1];
                currDecoys[i] = decoy;
            }
            else {
                i--;
            }
        }

        return currDecoys;
    }

    public Integer getAnswer() {
        return this.answer;
    }

    public Integer getImage() {
        return this.sign;
    }

    public Integer[] getDecoys() {
        return this.decoys;
    }



}
