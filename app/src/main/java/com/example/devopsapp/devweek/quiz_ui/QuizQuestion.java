package com.example.devopsapp.devweek.quiz_ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.devopsapp.devweek.R;
import com.example.devopsapp.devweek.data.network.Question;
import com.example.devopsapp.devweek.uidata.QuizViewModel;


public class QuizQuestion extends Fragment {
    private QuizViewModel mViewModel;

    private TextView test;

    public QuizQuestion() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_quiz, container, false);

        test = view.findViewById(R.id.question_text);

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

        test.setText(question.getResults().get(0).getQuestion());
    }


}
