package com.example.devopsapp.devweek.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SimpleSQLiteQuery;
import android.os.AsyncTask;

import com.example.devopsapp.devweek.data.network.Question;
import com.example.devopsapp.devweek.data.network.QuizApi;
import com.example.devopsapp.devweek.data.network.QuizRetrofitModule;
import com.example.devopsapp.devweek.data.network.Result;
import com.example.devopsapp.devweek.data.room.QuestionDao;
import com.example.devopsapp.devweek.data.room.QuestionEntity;
import com.example.devopsapp.devweek.data.room.QuizDatabase;

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
    private MutableLiveData<Question> questionLiveData;
    private int id = 1;


    public QuizRepository(Application application) {
        this.quizApi = new QuizRetrofitModule().createApi();
        this.quizDatabase = QuizDatabase.getDatabase(application);
        questionDao = quizDatabase.getQuestionDao();
        compositeDisposable = new CompositeDisposable();
        questionLiveData = new MutableLiveData<>();
        mExecutor = Executors.newFixedThreadPool(1);


    }

    public void loadNextQuestion() {
        //getNewQuestionFromNetwork();
        getNewQuestionFromDatabase();
    }

    public void loadFirstQuestionsIntoDatabase() {
        compositeDisposable.add(quizApi.getInitialQuestions()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::onInitalLoad, throwable -> onError()));
    }

   /* private void getNewQuestionFromNetwork() {
        compositeDisposable.add(quizApi.getNextQuestion()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::onQuestionLoaded, throwable -> onError()));
    }*/

    private void getNewQuestionFromDatabase() {
        questionDao.findAllQuestions().size();
        questionLiveData.postValue(transform2Question(questionDao.getQuestionById(id)));
        id++;
    }

    private Question transform2Question(QuestionEntity questionEntity) {
        Question question = new Question();
        question.setResponseCode(1);
        Result result = new Result();
        result.setType(questionEntity.getType());
        result.setCorrectAnswer(questionEntity.getCorrect_answer());
        result.setIncorrectAnswers(questionEntity.getIncorrect_answers());
        result.setQuestion(questionEntity.getQuestion());
        ArrayList<Result> resultList = new ArrayList<Result>();
        resultList.add(result);
        question.setResults(resultList);

        return question;
    }


    private void onError() {

    }

   /* private void onQuestionLoaded(Question question) {
        questionLiveData.postValue(question);
    }*/

    private void onInitalLoad(Question questions) {
        new PopulateDbAsync(quizDatabase, questions).execute();
    }



    public LiveData<Question> getQuestionLiveData() {
        return questionLiveData;
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {


        private final QuestionDao questionDao;
        private List<Result> questionSet;
        private QuizDatabase quizDatabase;

        PopulateDbAsync(QuizDatabase db, Question question) {
            questionSet = question.getResults();
            questionDao = db.getQuestionDao();
            quizDatabase = db;
        }


        @Override
        protected Void doInBackground(final Void... params) {

            // reset all auto-incrementalValues
            SimpleSQLiteQuery query = new SimpleSQLiteQuery("DELETE FROM sqlite_sequence");


            quizDatabase.beginTransaction();
            try {
                quizDatabase.clearAllTables();
                quizDatabase.query(query);
                quizDatabase.setTransactionSuccessful();
            } catch (Exception e) {

            } finally {
                quizDatabase.endTransaction();
            }

            for (Result result : questionSet
                    ) {
                QuestionEntity questionEntity = new QuestionEntity(0, result.getType(), result.getQuestion(), result.getCorrectAnswer(), result.getIncorrectAnswers());
                questionDao.insert(questionEntity);
            }

            return null;
        }


    }


    private void clearAll() {

        // (quizDatabase != null) ? : return false;


    }


}
