package com.example.devopsapp.devweek.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.devopsapp.devweek.data.network.Question;
import com.example.devopsapp.devweek.data.network.QuizApi;
import com.example.devopsapp.devweek.data.room.QuestionDao;
import com.example.devopsapp.devweek.data.room.QuestionEntity;
import com.example.devopsapp.devweek.data.room.QuizDao;
import com.example.devopsapp.devweek.data.room.QuizDatabase;
import com.example.devopsapp.devweek.data.room.QuizEntity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class QuizRepository {

    private QuestionDao questionsDao;
    private QuizDao quizDao;
    private QuizApi quizApi;
    private CompositeDisposable compositeDisposable;


    //LiveData observables
    private MutableLiveData<Question> questionLiveData;
    private LiveData<QuizEntity> quizEntityLiveData;



    public QuizRepository(QuizDatabase database, QuizApi quizApi) {
        QuizDatabase quizDatabase = database;
        quizDao = quizDatabase.getQuizDao();
        questionsDao = quizDatabase.getQuestionDao();
        this.quizApi = quizApi;
        compositeDisposable = new CompositeDisposable();
        questionLiveData = new MutableLiveData<>();
    }


    private List<QuestionEntity> getQuestionList(final int id) {
        return questionsDao.findAllQuestionsFromQuizForGivenId(id);
    }

    private LiveData<List<QuizEntity>> getQuizList() {
        return quizDao.returnAllQuizes();
    }

    private QuestionEntity getQuestion(final int id) {
        return questionsDao.getQuestionById(id);
    }

    public LiveData<QuizEntity> getQuiz(final int id) {
        return quizDao.returnQuizById(id);
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

    public LiveData<QuizEntity> getQuizLiveData() {
        return quizEntityLiveData;
    }
}
