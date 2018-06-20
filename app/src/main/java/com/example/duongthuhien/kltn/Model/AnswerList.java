package com.example.duongthuhien.kltn.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Duong Thu Hien on 5/29/2018.
 */

public class AnswerList implements Serializable {

    private String Question;
    private String WrongAnswer;
    private String CorrectAnswer;

    public AnswerList() {
        Question="";
        WrongAnswer="";
        CorrectAnswer="";
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getWrongAnswer() {
        return WrongAnswer;
    }

    public void setWrongAnswer(String wrongAnswer) {
        WrongAnswer = wrongAnswer;
    }

    public String getCorrectAnswer() {
        return CorrectAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        CorrectAnswer = correctAnswer;
    }


/*
    public AnswerList(Parcel in){
        String[] data = new String[3];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.setQuestion(data[0]);
        this.setCorrectAnswer(data[1]);
        this.setWrongAnswer(data[2]);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]{
                this.getQuestion(),
                this.getCorrectAnswer(),
                this.getWrongAnswer()
        });
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public AnswerList createFromParcel(Parcel in) {
            return new AnswerList(in);
        }

        public AnswerList[] newArray(int size) {
            return new AnswerList[size];
        }
    };*/
}
