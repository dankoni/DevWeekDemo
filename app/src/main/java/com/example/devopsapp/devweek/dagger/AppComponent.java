package com.example.devopsapp.devweek.dagger;

import android.app.Application;

import com.example.devopsapp.devweek.QuizApplication;
import com.example.devopsapp.devweek.data.QuizRepository;
import com.example.devopsapp.devweek.data.dagger.RepositoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuildersModule.class,
        RepositoryModule.class})
public interface AppComponent {
    void inject(QuizApplication quizApplication);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}