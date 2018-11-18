package com.example.devopsapp.devweek.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.example.devopsapp.devweek.data.room.QuestionDao;
import com.example.devopsapp.devweek.data.room.QuestionEntity;
import com.example.devopsapp.devweek.data.room.QuizDao;
import com.example.devopsapp.devweek.data.room.QuizDatabase;
import com.example.devopsapp.devweek.data.room.QuizEntity;

import java.util.List;

public class QuizRepository {

    private QuestionDao questionsDao;
    private QuizDao quizDao;

    private  LiveData<List<QuestionEntity>>  questionList;
    private  LiveData<List<QuizEntity>>  quizList;
    private  LiveData<QuestionEntity> question;

    public QuizRepository(Application application) {
        QuizDatabase quizDatabase = QuizDatabase.getDatabase(application);
        quizDao = quizDatabase.getQuizDao();
        questionsDao = quizDatabase.getQuestionDao();
    }








}
