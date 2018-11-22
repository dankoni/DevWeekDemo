package com.example.devopsapp.devweek.uidata.models;

import java.util.List;

/**
 * Created by Danko Misic on 11/22/18
 */
public class QuestionData {
    private String text;
    private String type;
    private AnswerData answerData;

    public QuestionData(String text, String type, List<String> answersSet, String correctAnswer) {
        this.text = text;
        this.answerData = new AnswerData(answersSet, correctAnswer);
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public AnswerData getAnswerData() {
        return answerData;
    }

    public String getType() {
        return type;
    }
}
