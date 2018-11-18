package com.example.devopsapp.devweek.uidata;

import android.arch.lifecycle.ViewModel;
import android.arch.persistence.room.Insert;

import com.example.devopsapp.devweek.data.QuizRepository;

import javax.inject.Inject;

public class QuizViewModel extends ViewModel {

    private QuizRepository quizRepository;

    @Inject
    public QuizViewModel(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }


}
