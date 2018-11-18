package com.example.devopsapp.devweek.data.network;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface QuizApi {

    @GET("api.php?amount=1&category=18&difficulty=medium")
    Single<Question>  getNextQuestion();
}
