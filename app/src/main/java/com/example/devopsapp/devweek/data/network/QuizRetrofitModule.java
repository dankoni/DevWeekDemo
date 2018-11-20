package com.example.devopsapp.devweek.data.network;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuizRetrofitModule {

    private static final int TIMEOUT_IN_MS = 30000;
    public static final String BASE_URL = "https://opentdb.com/";
    private QuizApi quizApi;


    public QuizRetrofitModule() {
        quizApi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(provideRxJavaCallAdapterFactory())
                .client(provideOkHttpClient())
                .build().create(QuizApi.class);
    }


    RxJava2CallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
    }


    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_IN_MS, TimeUnit.MILLISECONDS)
                .build();
    }


    public QuizApi createApi() {
        return quizApi;
    }

}
