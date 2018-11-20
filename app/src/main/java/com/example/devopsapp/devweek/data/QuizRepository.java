package com.example.devopsapp.devweek.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.devopsapp.devweek.data.network.Question;
import com.example.devopsapp.devweek.data.network.QuizApi;
import com.example.devopsapp.devweek.data.network.QuizRetrofitModule;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class QuizRepository {

    private QuizApi quizApi;
    private CompositeDisposable compositeDisposable;


    //LiveData observables
    private MutableLiveData<Question> questionLiveData;


    public QuizRepository() {
        this.quizApi = new QuizRetrofitModule().createApi();
        compositeDisposable = new CompositeDisposable();
        questionLiveData = new MutableLiveData<>();
    }

    public void loadNextQuestion() {
        getNewQuestionFromNetwork();

    }

    private void getNewQuestionFromNetwork() {
        compositeDisposable.add(quizApi.getNextQuestion()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::onQuestionLoaded, throwable -> onError()));
    }

    private void onError() {

    }

    private void onQuestionLoaded(Question question) {
        questionLiveData.postValue(question);
    }

    public LiveData<Question> getQuestionLiveData() {
        return questionLiveData;
    }

}
