package com.example.devopsapp.devweek.dagger;

import com.example.devopsapp.devweek.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();
}
