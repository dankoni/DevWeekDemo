package com.example.devopsapp.devweek.quiz_ui;

/**
 * Created by Danko Misic on 11/21/18
 */
public class AnswerEvent {

    private boolean correct;

    public AnswerEvent(boolean correct) {
        this.correct = correct;
    }

    public boolean isCorrect() {
        return correct;
    }
}
