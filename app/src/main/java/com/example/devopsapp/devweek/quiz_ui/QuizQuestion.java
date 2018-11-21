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
import com.example.devopsapp.devweek.uidata.models.AnswerData;


public class QuizQuestion extends Fragment {
    private QuizViewModel mViewModel;

    private TextView mQuestionTextView;
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

        AnswerData data = new AnswerData(result.getIncorrectAnswers(), result.getCorrectAnswer());

        initViews(data);

    }

    private void initViews(AnswerData data) {
        mQuestionTextView.setText(mTextOfQuestion);
        FragmentManager fragmentManager = getChildFragmentManager();
        BooleanFragment booleanFragment = BooleanFragment.newInstance(data);

        fragmentManager.beginTransaction().replace(R.id.answer_holder,booleanFragment).commit();

    }


}
