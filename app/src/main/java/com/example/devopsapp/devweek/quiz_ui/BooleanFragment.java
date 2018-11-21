package com.example.devopsapp.devweek.quiz_ui;

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

public class BooleanFragment extends Fragment {

    private QuizViewModel mViewModel;

    public static BooleanFragment newInstance() {
        return new BooleanFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.boollean_answer, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        // TODO: Use the ViewModel
    }

}
