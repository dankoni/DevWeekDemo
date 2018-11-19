package com.example.devopsapp.devweek.uidata;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.devopsapp.devweek.data.QuizRepository;
import com.example.devopsapp.devweek.data.network.Question;

import javax.inject.Inject;

public class QuizViewModel extends ViewModel {

    private QuizRepository quizRepository;

    public QuizViewModel() {
    }

    @Inject
    public QuizViewModel(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }


    public void loadQuestion() {
        quizRepository.loadNextQuestion();
    }

    public LiveData<Question> getQuestionLiveData() {
        return quizRepository.getQuestionLiveData();
    }





}
