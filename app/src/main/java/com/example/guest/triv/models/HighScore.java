package com.example.guest.triv.models;

/**
 * Created by Guest on 12/9/16.
 */
public class HighScore {
    private int mScore;
    private String mUserName;
    private String mCategory;
//    private int mBestStreak;


    public HighScore(int mScore, String mUserName, String mCategory) {
        this.mScore = mScore;
        this.mUserName = mUserName;
        this.mCategory = mCategory;
    }
    public HighScore(){}



    // GETTERS and SETTERS
    public int getScore() {
        return mScore;
    }

    public void setScore(int mScore) {
        this.mScore = mScore;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String mCategory) {
        this.mCategory = mCategory;
    }
}
