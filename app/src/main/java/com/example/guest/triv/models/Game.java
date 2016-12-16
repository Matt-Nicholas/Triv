package com.example.guest.triv.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Game {
    private String mCategory;
    private int mCategoryId = -1;
    private int mNumOfCoins = 5;
    private int mScore;
    private int mCorrectAnswerStreak;
    private ArrayList<Question> mIncorrectlyAnsweredQuestions = new ArrayList<>();

    //  CONSTRUCTOR
    public Game(String category) {
        mCategory = category;
    }

    // Empty constructor needed by the Parceler library
    public Game(){}

    public void decreaseCoinsByOne(){
        mNumOfCoins --;
    }
    public void increaseCoinsByOne(){
        mNumOfCoins ++;
    }

    private void increaseScore(String difficulty){
        switch(difficulty){
            case "easy": mScore += 5;
                break;
            case "medium": mScore += 10;
                    break;
            case "hard": mScore += 15;
                break;
        }
    }

    public void correctAnswer(String difficulty){
        increaseStreak();
        increaseScore(difficulty);
        if((mCorrectAnswerStreak % 5) == 0){
            increaseCoinsByOne();
        }
    }

    public void incorrectAnswer(Question question){
        mIncorrectlyAnsweredQuestions.add(question);
        mCorrectAnswerStreak = 0;
        mNumOfCoins --;
    }

    private void increaseStreak(){
        mCorrectAnswerStreak ++;
    }

    //  GETTERS and SETTERS

    public void setCorrectAnswerStreak(int correctAnswerStreak) {
        this.mCorrectAnswerStreak = correctAnswerStreak;
    }

    public int getCorrectAnswerStreak() {
        return mCorrectAnswerStreak;
    }
    public int getScore() {
        return mScore;
    }
    public void setScore(int score) {
        mScore = score;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public int getNumOfCoins() {
        return mNumOfCoins;
    }

    public void setNumOfCoins(int numOfCoins) {mNumOfCoins = numOfCoins;}

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
