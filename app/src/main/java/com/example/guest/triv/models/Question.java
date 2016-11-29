package com.example.guest.triv.models;

import java.util.ArrayList;

/**
 * Created by Matt Nicholas on 11/18/16.
 */


public class Question {
    private String mCategory;
    private String mType;
    private String mDifficulty;
    private String mQuestion;
    private String mCorrectAnswer;
    private ArrayList<String> mIncorrectAnswers;



    public Question(String category, String type, String difficulty, String question, String correctAnswer, ArrayList<String> incorrectAnswer){
        this.mCategory = category;
        this.mType = type;
        this.mDifficulty = difficulty;
        this.mQuestion = question;
        this.mCorrectAnswer = correctAnswer;
        this.mIncorrectAnswers = incorrectAnswer;

    }

    public String getCategory(){
        return mCategory;
    }
    public String getType(){
        return mType;
    }
    public String getDifficulty(){
        return mDifficulty;
    }
    public String getQuestion(){
        return mQuestion;
    }
    public String getCorrectAnswer(){
        return mCorrectAnswer;
    }
    public ArrayList<String> getIncorrectAnswers(){
        return mIncorrectAnswers;
    }


}
