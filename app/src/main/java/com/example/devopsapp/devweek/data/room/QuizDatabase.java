package com.example.devopsapp.devweek.data.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {QuizEntity.class,QuestionEntity.class}, version = 1 , exportSchema = false)
@TypeConverters({IncorrectSetConverter.class})
public abstract class QuizDatabase extends RoomDatabase {

    public static final String database_name = "quiz_database";

    public abstract QuizDao getQuizDao();

    public abstract QuestionDao getQuestionDao();
}
