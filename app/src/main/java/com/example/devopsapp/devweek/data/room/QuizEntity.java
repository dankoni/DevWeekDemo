package com.example.devopsapp.devweek.data.room;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "quizes")
public class QuizEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private  int id;

    private String title;

    public QuizEntity(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
