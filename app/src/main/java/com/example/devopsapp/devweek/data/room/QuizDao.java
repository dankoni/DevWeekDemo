package com.example.devopsapp.devweek.data.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface QuizDao {

    @Insert
    void insert(QuizEntity quiz);

    @Update
    void update(QuizEntity quiz);

    @Delete
    void delete(QuizEntity quiz);

    @Query("SELECT * from quizes ORDER by id ASC")
    LiveData<List<QuizEntity>> returnAllQuizes();

    @Query("SELECT * from quizes WHERE  id=:id")
    LiveData<QuizEntity> returnQuizById(final int id);

}
