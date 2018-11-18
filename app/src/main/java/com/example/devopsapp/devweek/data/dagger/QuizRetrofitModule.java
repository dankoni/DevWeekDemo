package com.example.devopsapp.devweek.data.dagger;

import com.example.devopsapp.devweek.data.network.QuizApi;
import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class QuizRetrofitModule {

    @Provides
    @Singleton
    public QuizApi provideTvMazeApi(OkHttpClient okHttpClient,
                                      @Named(NetworkModule.BASE_URL) String baseUrl,
                                      RxJava2CallAdapterFactory rxJava2CallAdapterFactory,
                                      Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .client(okHttpClient)
                .build().create(QuizApi.class);
    }
}
