package com.example.guest.triv.models;

import java.util.ArrayList;
import org.parceler.Parcel;

@Parcel
public class Game {
    private String mCategory;
    private int mNumOfIncorrectAnswers = 0;
    private ArrayList<Question> mIncorrectlyAnsweredQuestions = new ArrayList<>();


    public Game(){} // empty constructor needed by the Parceler library
    public Game(String category) {
        mCategory = category;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public int getNumOfIncorrectAnswers() {
        return mNumOfIncorrectAnswers;
    }

    public void setNumOfIncorrectAnswers(int numOfIncorrectAnswers) {
        mNumOfIncorrectAnswers = numOfIncorrectAnswers;
    }

    public ArrayList<Question> getIncorrectlyAnsweredQuestions() {
        return mIncorrectlyAnsweredQuestions;
    }

    public void setIncorrectlyAnsweredQuestions(ArrayList<Question> incorrectlyAnsweredQuestions) {
        mIncorrectlyAnsweredQuestions = incorrectlyAnsweredQuestions;
    }

    public void addIncorrectlyAnsweredQuestion(Question question){
        mIncorrectlyAnsweredQuestions.add(question);
    }
}
