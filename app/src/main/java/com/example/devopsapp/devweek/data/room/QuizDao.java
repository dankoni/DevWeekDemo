package com.example.devopsapp.devweek.data.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

@Dao
public interface QuizDao {

    @Insert
    void insert(QuizEntity quiz);

    @Update
    void update(QuizEntity quiz);

    @Delete
    void delete(QuizEntity quiz);
}
