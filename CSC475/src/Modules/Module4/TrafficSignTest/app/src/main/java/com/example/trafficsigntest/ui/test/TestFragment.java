package com.example.trafficsigntest.ui.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.trafficsigntest.R;
import com.example.trafficsigntest.components.Question;
import com.example.trafficsigntest.components.User;
import com.example.trafficsigntest.databinding.FragmentTestBinding;
import com.example.trafficsigntest.helpers.UserDataManager;


import java.util.ArrayList;
import java.util.Random;

public class TestFragment extends Fragment {

    private FragmentTestBinding binding;

    private SharedPreferences sharedPrefs;
    private int questionNum = 0;

    private final ArrayList<Integer> iArray = new ArrayList<>();

    private int correct = 0;

    private User user;
    private int currentQuestion = 0;

    private int userScore = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TestViewModel galleryViewModel =
                new ViewModelProvider(this).get(TestViewModel.class);

        binding = FragmentTestBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedPrefs = getActivity().getSharedPreferences("Users", Context.MODE_PRIVATE);

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        ConstraintLayout welcome = root.findViewById(R.id.welcome_layout);
        ConstraintLayout test = root.findViewById(R.id.question_layout);
        ConstraintLayout end = root.findViewById(R.id.test_end);
        Button startButton = root.findViewById(R.id.start_button);



        welcome.setVisibility(View.VISIBLE);
        test.setVisibility(View.GONE);
        end.setVisibility(View.GONE);
        startButton.setOnClickListener(e -> {
            user = createUser(root, welcome, test);
//            System.out.println(user.getUserName() + " : " + user.verify());
//            if (user.getUserName().compareTo("") != 0) {
//                System.out.println("Starting Test");
//                startTest(root);
//            }

        });


        return root;
    }

    private void startTest(View v) {

        setupQuestionTitle(v, currentQuestion);
        setupQuestion(v);
    }


    private void setupQuestionTitle(View v, int i) {
        TextView title = v.findViewById(R.id.question_title);

        switch (i) {

            case 1:
                title.setText(R.string.question_2);
                break;
            case 2:
                title.setText(R.string.question_3);
                break;
            case 3:
                title.setText(R.string.question_4);
                break;
            case 4:
                title.setText(R.string.question_5);
                break;
            case 5:
                title.setText(R.string.question_6);
                break;
            case 6:
                title.setText(R.string.question_7);
                break;
            case 7:
                title.setText(R.string.question_8);
                break;
            case 8:
                title.setText(R.string.question_9);
                break;
            default:
                title.setText(R.string.question_1);
                break;
        }

    }


    private void setupQuestion(View v) {


        Question question = null;
        int randomAnswer = new Random().nextInt(4);
        ImageView image = v.findViewById(R.id.question_image);
        RadioButton ansOne = v.findViewById(R.id.answer_one);
        RadioButton ansTwo = v.findViewById(R.id.answer_two);
        RadioButton ansThree = v.findViewById(R.id.answer_three);
        RadioButton ansFour = v.findViewById(R.id.answer_four);
        boolean q = false;
        while (!q) {
            Integer randomIndex = new Random().nextInt(9);
            if (!iArray.contains(randomIndex)) {
                questionNum = randomIndex;
                question = new Question(questionNum);
                q = true;
            }
        }
        image.setImageResource(question.getImage());
        Integer[] decoys = question.getDecoys();
        int answer = question.getAnswer();
        System.out.println("Correct Answer: " + (randomAnswer + 1));

        switch (randomAnswer) {
            case 0:
                ansOne.setText(answer);
                ansTwo.setText(decoys[0]);
                ansThree.setText(decoys[1]);
                ansFour.setText(decoys[2]);
                break;
            case 1:
                ansOne.setText(decoys[0]);
                ansTwo.setText(answer);
                ansThree.setText(decoys[1]);
                ansFour.setText(decoys[2]);
                break;
            case 2:
                ansOne.setText(decoys[1]);
                ansTwo.setText(decoys[0]);
                ansThree.setText(answer);
                ansFour.setText(decoys[2]);
                break;
            case 3:
                ansOne.setText(decoys[2]);
                ansTwo.setText(decoys[0]);
                ansThree.setText(decoys[1]);
                ansFour.setText(answer);
                break;
            default:
                System.out.println("ERROR on answer location");
                break;

        }
        correct = randomAnswer;
        setupButton(v);

    }

    private void setupButton(View v) {

        Button submit = v.findViewById(R.id.submit_answer_button);

        submit.setOnClickListener(e -> {
            currentQuestion++;
            int answer = getCheckedAnswer(v);
            System.out.println("Entered Answer: " + (answer + 1));
            if (String.valueOf(answer).compareTo(String.valueOf(correct)) == 0) {
                userScore++;
                System.out.println("Updating user score");
            }
            if (currentQuestion >= 9) {
                ConstraintLayout test = v.findViewById(R.id.question_layout);
                ConstraintLayout end = v.findViewById(R.id.test_end);

                test.setVisibility(View.GONE);
                end.setVisibility(View.VISIBLE);

                TextView score = v.findViewById(R.id.test_end_score);
                score.setText(String.valueOf(userScore));
                user.setScore(userScore);
            } else {
                System.out.println("Score: " + userScore + "\n");
                iArray.add(questionNum);
                setupQuestionTitle(v, currentQuestion);
                setupQuestion(v);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private User createUser(View v, View welcome, View test) {
        EditText nameInput = v.findViewById(R.id.user_name_input);
        String name = nameInput.getText().toString();

        System.out.println("Name: " + name);
        User tempUser = new User();
        boolean done = false;
        while (!done) {
            // Check for user input
            if
                // Toast warning to user if input is blank
            (name.compareTo("") == 0) {
                Toast toast = new Toast(v.getContext());
                toast.setText("Please enter a name");
                toast.show();
                break;
            } else {
                if (sharedPrefs.getAll() != null) {
                    if (UserDataManager.checkForUser(name, v)) {
                        tempUser = UserDataManager.loadUserData(name, v);
                        Toast toast = new Toast(v.getContext());
                        toast.setText("Loading user information");
                        toast.show();
                        welcome.setVisibility(View.GONE);
                        test.setVisibility(View.VISIBLE);
                    }
                } else {
                    tempUser = new User(name, 0);
                    UserDataManager.saveUserData(name, 0, v);
                    Toast toast = new Toast(v.getContext());
                    toast.setText("Creating User");
                    toast.show();
                    welcome.setVisibility(View.GONE);
                    test.setVisibility(View.VISIBLE);
                }
                done = true;

            }
        }

        return tempUser;
    }

    private int getCheckedAnswer(View v) {
        RadioGroup answers = v.findViewById(R.id.answers);
        RadioButton selected = v.findViewById(answers.getCheckedRadioButtonId());
        selected.setChecked(false);
        return answers.indexOfChild(selected);
    }
}