package com.example.devopsapp.devweek.quizentry;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.devopsapp.devweek.R;
import com.example.devopsapp.devweek.uidata.QuizViewModel;

public class QuizEntry extends Fragment {

    private QuizViewModel mViewModel;

    public static QuizEntry newInstance() {
        return new QuizEntry();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.quiz_start_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        // TODO: Use the ViewModel
    }

}
