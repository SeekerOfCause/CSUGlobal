package com.example.trafficsigntest.ui.test;

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

import com.example.trafficsigntest.MainActivity;
import com.example.trafficsigntest.R;
import com.example.trafficsigntest.components.Question;
import com.example.trafficsigntest.components.User;
import com.example.trafficsigntest.databinding.FragmentTestBinding;


import java.util.ArrayList;
import java.util.Random;

public class TestFragment extends Fragment {

    private FragmentTestBinding binding;
    private int questionNum = 0;

    private ArrayList<Integer> iArray = new ArrayList<Integer>();

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

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        ConstraintLayout welcome = root.findViewById(R.id.welcome_layout);
        ConstraintLayout test = root.findViewById(R.id.question_layout);
        ConstraintLayout end = root.findViewById(R.id.test_end);
        Button startButton = root.findViewById(R.id.start_button);
        EditText nameInput = root.findViewById(R.id.user_name_input);
        String name = nameInput.getText().toString();


        welcome.setVisibility(View.VISIBLE);
        test.setVisibility(View.GONE);
        end.setVisibility(View.GONE);
        startButton.setOnClickListener(e -> {
            welcome.setVisibility(View.GONE);
            test.setVisibility(View.VISIBLE);
            user = createUser(root, name);
            startTest(root);
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
        Integer randomAnswer = new Random().nextInt(3);
        ImageView image = v.findViewById(R.id.question_image);
        RadioButton ansOne = v.findViewById(R.id.answer_one);
        RadioButton ansTwo = v.findViewById(R.id.answer_two);
        RadioButton ansThree = v.findViewById(R.id.answer_three);
        RadioButton ansFour = v.findViewById(R.id.answer_four);
        boolean q = false;
        while (!q) {
            Integer randomIndex = new Random().nextInt(9);
            if (iArray.contains(randomIndex) == false) {
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
            RadioGroup answers = v.findViewById(R.id.answers);
            RadioButton selected = v.findViewById(answers.getCheckedRadioButtonId());
            System.out.println("Entered Answer: " + (answers.indexOfChild(selected) + 1));
            if (String.valueOf(answers.indexOfChild(selected)).compareTo(String.valueOf(correct)) == 0) {
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

    private User createUser(View v, String name) {
        Button start = v.findViewById(R.id.start_button);
        ArrayList<User> users = MainActivity.getUsers();
        int score = 0;
        boolean done = false;
        if
        (name.compareTo("") == 0) {
            Toast toast = new Toast(v.getContext());
            toast.setText("Please enter a name");
            toast.show();
        } else {
            while (!done) {
                if (users.size() == 0) {
                    user = new User(name, score);
                    users.add(user);
                } else {
                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).getUserName().compareToIgnoreCase(name) != 0) {
                            user = new User(name, score);
                            users.add(user);
                            done = true;
                        } else {
                            Toast toast = new Toast(v.getContext());
                            toast.setText("User already exists");
                            toast.show();
                        }
                    }
                }
            }
        }
        return user;
    }
}