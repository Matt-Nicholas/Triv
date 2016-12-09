package com.example.guest.triv.models;






import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Question {
    // Member Variable declarations
    private String mCategory;
    private String mType;
    private String mDifficulty;
    private String mQuestion;
    private String mCorrectAnswer;
    private ArrayList<String> mIncorrectAnswers;
    private String incorrectGuess;

    public Question(){}
    // CONSTRUCTOR
    public Question(String category, String type, String difficulty, String question, String correctAnswer, ArrayList<String> incorrectAnswers){
        this.mCategory = category;
        this.mType = type;
        this.mDifficulty = difficulty;
        this.mQuestion = question;
        this.mCorrectAnswer = correctAnswer;
        this.mIncorrectAnswers = incorrectAnswers;
    }
    //GETTERS
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
    public String getIncorrectGuess() { return incorrectGuess; }

    //SETTERS
    public void setIncorrectGuess(String incorrectGuess) {this.incorrectGuess = incorrectGuess; }
}
