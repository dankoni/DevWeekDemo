package com.example.devopsapp.devweek.data;

import android.arch.lifecycle.LiveData;

import com.example.devopsapp.devweek.data.network.QuizApi;
import com.example.devopsapp.devweek.data.room.QuestionDao;
import com.example.devopsapp.devweek.data.room.QuestionEntity;
import com.example.devopsapp.devweek.data.room.QuizDao;
import com.example.devopsapp.devweek.data.room.QuizDatabase;
import com.example.devopsapp.devweek.data.room.QuizEntity;

import java.util.List;

public class QuizRepository {

    private QuestionDao questionsDao;
    private QuizDao quizDao;

    public QuizRepository(QuizDatabase database, QuizApi quizApi) {
        QuizDatabase quizDatabase = database;
        quizDao = quizDatabase.getQuizDao();
        questionsDao = quizDatabase.getQuestionDao();
    }


    public LiveData<List<QuestionEntity>> getQuestionList(final int id) {
        return questionsDao.findAllQuestionsFromQuizForGivenId(id);
    }

    public LiveData<List<QuizEntity>> getQuizList() {
        return quizDao.returnAllQuizes();
    }

    public LiveData<QuestionEntity> getQuestion(final int id) {
        return questionsDao.getQuestionById(id);
    }

    public LiveData<QuizEntity> getQuiz(final int id) {
        return quizDao.returnQuizById(id);
    }
}
