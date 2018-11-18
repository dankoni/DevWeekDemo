package com.example.devopsapp.devweek.data.dagger;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.devopsapp.devweek.data.room.QuizDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Singleton
    @Provides
    public QuizDatabase provideQuizDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                QuizDatabase.class, QuizDatabase.database_name)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }
}
