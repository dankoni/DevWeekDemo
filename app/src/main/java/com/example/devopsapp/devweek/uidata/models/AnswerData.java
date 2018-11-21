package com.example.devopsapp.devweek.uidata.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Danko Misic on 11/21/18
 */
public class AnswerData implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public AnswerData createFromParcel(Parcel in) {
            return new AnswerData(in);
        }

        public AnswerData[] newArray(int size) {
            return new AnswerData[size];
        }
    };
    List<String> answersSet;
    String correctAnswer;

    public AnswerData(List<String> answersSet, String correctAnswer) {
        this.answersSet = answersSet;
        this.correctAnswer = correctAnswer;
    }

    private AnswerData(Parcel in) {
        correctAnswer = in.readString();
        in.readStringList(answersSet);

    }

    public List<String> getAnswersSet() {
        return answersSet;
    }

    public void setAnswersSet(List<String> answersSet) {
        this.answersSet = answersSet;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(correctAnswer);
        dest.writeStringList(answersSet);

    }
}
