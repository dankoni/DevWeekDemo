package com.example.devopsapp.devweek.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import com.example.devopsapp.devweek.data.network.Question;
import com.example.devopsapp.devweek.data.network.QuizApi;
import com.example.devopsapp.devweek.data.network.QuizRetrofitModule;
import com.example.devopsapp.devweek.data.network.Result;
import com.example.devopsapp.devweek.data.room.QuestionDao;
import com.example.devopsapp.devweek.data.room.QuestionEntity;
import com.example.devopsapp.devweek.data.room.QuizDatabase;
import com.example.devopsapp.devweek.uidata.models.QuestionData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class QuizRepository {

    private QuizApi quizApi;
    private QuizDatabase quizDatabase;
    private CompositeDisposable compositeDisposable;


    private QuestionDao questionDao;


    private ExecutorService mExecutor;

    //LiveData observables
    private LiveData<QuestionData> questionLiveData;


    public QuizRepository(Application application) {
        this.quizApi = new QuizRetrofitModule().createApi();
        this.quizDatabase = QuizDatabase.getDatabase(application);
        questionDao = quizDatabase.getQuestionDao();
        compositeDisposable = new CompositeDisposable();
        questionLiveData = new MutableLiveData<>();
        mExecutor = Executors.newFixedThreadPool(1);


    }

    public void loadNextQuestion() {
        loadFirstQuestionsIntoDatabase();
    }

    private void loadFirstQuestionsIntoDatabase() {
        compositeDisposable.add(quizApi.getInitialQuestions()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::onInitalLoad, throwable -> onError()));
    }

    private List<QuestionData> transform2Question(List<QuestionEntity> questionEntity) {

        List<QuestionData> questionList = new ArrayList<>();

        for (QuestionEntity q : questionEntity
                ) {
            QuestionData question = new QuestionData(q.getQuestion(),
                    q.getType(), q.getIncorrect_answers(), q.getCorrect_answer());
            questionList.add(question);
        }
        return questionList;
    }

    private void onError() {

    }

    private void onInitalLoad(Question questions) {
        questionDao.deleteAll();

        for (Result result : questions.getResults()
                ) {
            QuestionEntity questionEntity = new QuestionEntity(0, result.getType(), result.getQuestion(), result.getCorrectAnswer(), result.getIncorrectAnswers());
            questionDao.insert(questionEntity);
        }
    }

    public LiveData<List<QuestionData>> getQuestionLiveData() {
        return Transformations.map(questionDao.findAllQuestions(), questions -> transform2Question(questions));
    }


}
