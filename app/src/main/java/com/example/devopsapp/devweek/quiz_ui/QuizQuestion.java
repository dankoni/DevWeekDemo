package com.example.devopsapp.devweek.quiz_ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.devopsapp.devweek.R;
import com.example.devopsapp.devweek.data.network.Question;
import com.example.devopsapp.devweek.data.network.Result;
import com.example.devopsapp.devweek.uidata.QuizViewModel;

import java.util.HashSet;
import java.util.Set;


public class QuizQuestion extends Fragment {
    private QuizViewModel mViewModel;

    private TextView mQuestionTextView;
    private Set<String> mAnswers;
    private String mType;
    private String mTextOfQuestion;
    private View mAnswersLayout;

    public QuizQuestion() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_quiz, container, false);

        mQuestionTextView = view.findViewById(R.id.question_text);
        mAnswersLayout = view.findViewById(R.id.answer_holder);

        view.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
            public void onSwipeRight() {

            }

            public void onSwipeLeft() {

            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        mViewModel.loadQuestion();
        mViewModel.getQuestionLiveData().observe(this, this::onQuestionLoaded);
    }

    private void onQuestionLoaded(Question question) {
        Result result = question.getResults().get(0);
        mTextOfQuestion = result.getQuestion();
        mType = result.getType();
        mAnswers = new HashSet<>();
        mAnswers.add(result.getCorrectAnswer());
        for (String text: result.getIncorrectAnswers()
             ) {
            mAnswers.add(text);
        }

        initViews();

    }

    private void initViews() {
        mQuestionTextView.setText(mTextOfQuestion);
        FragmentManager fragmentManager = getChildFragmentManager();
        BooleanFragment booleanFragment = new BooleanFragment();
        fragmentManager.beginTransaction().replace(R.id.answer_holder,booleanFragment).commit();

    }


}
