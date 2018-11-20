package com.example.devopsapp.devweek.dagger;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.devopsapp.devweek.uidata.QuizViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(QuizViewModel.class)
    abstract ViewModel bindQuizViewModel(QuizViewModel quizViewModel);


    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(QuizViewModelFactory factory);
}
