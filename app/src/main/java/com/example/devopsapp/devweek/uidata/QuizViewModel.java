package com.example.devopsapp.devweek.uidata;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.devopsapp.devweek.data.QuizRepository;
import com.example.devopsapp.devweek.data.network.Question;


public class QuizViewModel extends ViewModel {

    private QuizRepository quizRepository;

    public QuizViewModel() {
        this.quizRepository = new QuizRepository();
    }


    public void loadQuestion() {
        quizRepository.loadNextQuestion();
    }

    public LiveData<Question> getQuestionLiveData() {
        return quizRepository.getQuestionLiveData();
    }


}
