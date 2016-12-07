package com.example.guest.triv.models;

import java.util.ArrayList;
import org.parceler.Parcel;

@Parcel
public class Game {
    private String mCategory;
    private int mCategoryId = -1;
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
    public void setCategoryId(String category){
        switch (category){
            case "SPORTS": mCategoryId = 21;
                break;
            case "ART": mCategoryId = 25;
                break;
            case "HISTORY": mCategoryId = 23;
                break;
            case "POLITICS": mCategoryId = 24;
                break;
            case "ANIMALS": mCategoryId = 27;
                break;
            case "GEOGRAPHY": mCategoryId = 22;
                break;
        }
    }
    public int getCategoryId(){
        return mCategoryId;
    }
}
