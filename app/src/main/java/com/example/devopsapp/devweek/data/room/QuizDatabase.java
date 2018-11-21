package com.example.devopsapp.devweek.data.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {QuestionEntity.class}, version = 1, exportSchema = false)
@TypeConverters({IncorrectSetConverter.class})
public abstract class QuizDatabase extends RoomDatabase {

    public static final String database_name = "quiz_database";

    public abstract QuestionDao getQuestionDao();


    private static volatile QuizDatabase INSTANCE;

    public static QuizDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (QuizDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QuizDatabase.class, database_name)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
