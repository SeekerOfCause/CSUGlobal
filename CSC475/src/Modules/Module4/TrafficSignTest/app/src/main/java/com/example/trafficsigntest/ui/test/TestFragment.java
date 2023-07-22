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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Space;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.text.PrecomputedTextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.trafficsigntest.R;
import com.example.trafficsigntest.components.Question;
import com.example.trafficsigntest.components.Test;
import com.example.trafficsigntest.components.User;
import com.example.trafficsigntest.databinding.FragmentTestBinding;

public class TestFragment extends Fragment {

    private FragmentTestBinding binding;

    private SharedPreferences sharedPrefs;

    private User user;

    private Test test;

    private int userScore = 0;

    private Question current = null;

    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TestViewModel galleryViewModel =
                new ViewModelProvider(this).get(TestViewModel.class);

        binding = FragmentTestBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        setViews(3);

        sharedPrefs = requireActivity().getPreferences(Context.MODE_PRIVATE);

        Button start = root.findViewById(R.id.start_button);
        start.setOnClickListener(e -> user = getUser());

        return root;
    }

    private void printUser(User user) {
        System.out.println(sharedPrefs.contains(user.getUserName()) + "Score: " + sharedPrefs.getInt(user.getUserName(), -1));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private User getUser() {
        String userName = getUserName();
        if (userName.compareTo("") != 0) {
            try {
                int score = sharedPrefs.getInt(userName, -1);
                user = new User(userName, score);
            } catch (Exception e) {
                user = new User(userName, 0);
            }
            if (user.getScore() == -1) {
                user = new User(userName, 0);
            }
            saveUser(user);
            printUser(user);
            setupTest();
        }
        else {
            Toast toast = new Toast(this.getContext());
            toast.setText("Please enter your name");
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
        }
        return user;
    }

    private void setupTest() {
        test = new Test(9);
        setViews(0);
        setQuestion();
        startTest();
    }

    private void startTest() {
        Button submitButton = root.findViewById(R.id.submit_answer_button);
        submitButton.setOnClickListener(e -> {
            if (test.getTestLength() > 0) {
                checkSubmission(current);
                setQuestion();
            }
            else {
                endTest();
            }
        });
    }

    private void endTest() {
        setViews(1);
        saveUser(user);
    }

    private void setQuestion() {
        TextView title = root.findViewById(R.id.question_title);
        current = test.getQuestion();
        title.setText("Question " + current.getQuestionNumber());
        Integer image = current.getImage();
        setImage(image);
        for (int i = 0; i < 4; i++) {
            setAnswer(i, current.getAnswer(i));
        }
    }

    private void checkSubmission(Question current) {
        int answer = getCheckedAnswer();
        int correct = current.getCorrectAnswer();
        System.out.println("Chosen: " + answer + " | " + "Correct: " + correct);
        if (answer == correct) {
            userScore++;
            user.setScore(userScore);
        }
    }


    private String getUserName() {
        EditText nameInput = root.findViewById(R.id.user_name_input);
        String name = null;
        try {
            name = nameInput.getText().toString();
        }
        catch (Exception e) {
            System.out.println("No user name");
        }
        return name;
    }

    private int getCheckedAnswer() {
        RadioGroup answers = root.findViewById(R.id.answers);
        RadioButton selected = root.findViewById(answers.getCheckedRadioButtonId());
        selected.setChecked(false);
        return answers.indexOfChild(selected);

    }

    private void setImage(Integer view) {
        ImageView image = root.findViewById(R.id.question_image);
        image.setImageResource(view);
    }

    private void setAnswer(int i, Integer view) {

        RadioButton currAns;
        switch (i) {
            case 0:
                currAns = root.findViewById(R.id.answer_one);
                break;
            case 1:
                currAns = root.findViewById(R.id.answer_two);
                break;
            case 2:
                currAns = root.findViewById(R.id.answer_three);
                break;
            default:
                currAns = root.findViewById(R.id.answer_four);
                break;
        }
        currAns.setText(view);
    }

    private void setViews(int i) {

        ConstraintLayout begin = root.findViewById(R.id.welcome_layout);
        ConstraintLayout testLayout = root.findViewById(R.id.question_layout);
        ConstraintLayout testEnd = root.findViewById(R.id.test_end);
        TextView score = root.findViewById(R.id.test_end_score);

        switch (i) {
            case 0:
                begin.setVisibility(View.GONE);
                testLayout.setVisibility(View.VISIBLE);
                testEnd.setVisibility(View.GONE);
                break;
            case 1:
                begin.setVisibility(View.GONE);
                testLayout.setVisibility(View.GONE);
                testEnd.setVisibility(View.VISIBLE);
                score.setText(String.valueOf(userScore));
                break;
            case 3:
                begin.setVisibility(View.VISIBLE);
                testLayout.setVisibility(View.GONE);
                testEnd.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    public void saveUser(User user) {
        SharedPreferences.Editor prefsEdit = sharedPrefs.edit();
        prefsEdit.putInt(user.getUserName(), userScore);
        prefsEdit.apply();
        System.out.println(sharedPrefs.getInt(user.getUserName(), -1));
    }


}