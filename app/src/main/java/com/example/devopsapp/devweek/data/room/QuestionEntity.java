package com.example.devopsapp.devweek.data.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

import io.reactivex.annotations.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "questions",
        foreignKeys = @ForeignKey(entity = QuizEntity.class,
        parentColumns = "id",
        childColumns = "quizId",
        onDelete = CASCADE),indices = {@Index(value = {"quizId"})})
public class QuestionEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    private String type;

    private final String question;

    private final String correct_answer;

    private final int quizId;

    private List<String> incorrect_answers;

    public QuestionEntity(int id, String type, String question, String correct_answer, int quizId, List<String> incorrect_answers) {
        this.id = id;
        this.type = type;
        this.question = question;
        this.correct_answer = correct_answer;
        this.quizId = quizId;
        this.incorrect_answers = incorrect_answers;
    }


    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public List<String> getIncorrect_answers() {
        return incorrect_answers;
    }

    public int getQuizId() {
        return quizId;
    }
}
