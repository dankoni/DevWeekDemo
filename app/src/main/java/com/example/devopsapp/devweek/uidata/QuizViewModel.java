package com.example.devopsapp.devweek.uidata;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.devopsapp.devweek.data.QuizRepository;
import com.example.devopsapp.devweek.data.network.Question;


public class QuizViewModel extends AndroidViewModel {

    private QuizRepository quizRepository;

    public QuizViewModel(Application application) {
        super(application);
        this.quizRepository = new QuizRepository(application);
    }


    public void loadQuestion() {
        quizRepository.loadNextQuestion();
    }

    public LiveData<Question> getQuestionLiveData() {
        return quizRepository.getQuestionLiveData();
    }


}
