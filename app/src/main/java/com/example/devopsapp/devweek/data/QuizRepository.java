package com.example.devopsapp.devweek.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
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


    public QuizRepository(Application application) {
        this.quizApi = new QuizRetrofitModule().createApi();
        this.quizDatabase = QuizDatabase.getDatabase(application);
        questionDao = quizDatabase.getQuestionDao();
        compositeDisposable = new CompositeDisposable();
        questionLiveData = new MutableLiveData<>();
        mExecutor = Executors.newFixedThreadPool(1);


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
        new PopulateDbAsync(quizDatabase, question, questionLiveData).execute();
    }



    public LiveData<Question> getQuestionLiveData() {
        return questionLiveData;
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {


        private final QuestionDao questionDao;
        private List<Result> questionSet;
        private MutableLiveData<Question> questionLiveData;

        PopulateDbAsync(QuizDatabase db, Question question, MutableLiveData<Question> liveData) {
            questionSet = question.getResults();
            questionDao = db.getQuestionDao();
            this.questionLiveData = liveData;

        }


        @Override
        protected Void doInBackground(final Void... params) {
            // questionDao.deleteAll();

            for (Result result : questionSet
                    ) {
                QuestionEntity questionEntity = new QuestionEntity(0, result.getType(), result.getQuestion(), result.getCorrectAnswer(), result.getIncorrectAnswers());
                questionDao.insert(questionEntity);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            List<QuestionEntity> questionEntityList = questionDao.findAllQuestionsFromQuizForGivenId();
            QuestionEntity questionEntity = questionEntityList.get(0);
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
            questionLiveData.postValue(question);
        }
    }

}
