package com.example.devopsapp.devweek.data.dagger;

import android.arch.persistence.room.Database;

import com.example.devopsapp.devweek.data.QuizRepository;
import com.example.devopsapp.devweek.data.network.QuizApi;
import com.example.devopsapp.devweek.data.room.QuizDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {DatabaseModule.class,NetworkModule.class})
public class RepositoryModule {

    @Provides
    @Singleton
    QuizRepository providesQuizRepository(QuizDatabase database, QuizApi quizApi){
        return new QuizRepository(database,quizApi);
    }


}
