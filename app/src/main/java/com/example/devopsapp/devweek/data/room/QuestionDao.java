package com.example.devopsapp.devweek.data.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface QuestionDao {

    @Insert
    void insert(QuestionEntity question);

    @Update
    void update(QuestionEntity question);

    @Delete
    void delete(QuestionEntity question);

    @Query("SELECT * from questions")
    List<QuestionEntity> findAllQuestions();

    @Query("SELECT * from questions WHERE id=:id")
    QuestionEntity getQuestionById(final int id);

    @Query("SELECT * from questions ORDER BY id ASC LIMIT 1")
    LiveData<QuestionEntity> getFirstQuestion();

    @Query("DELETE from questions")
    void deleteAll();
}
