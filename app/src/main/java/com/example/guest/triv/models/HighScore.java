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



    // GETTERS and SETTERS
    public int getmScore() {
        return mScore;
    }

    public void setmScore(int mScore) {
        this.mScore = mScore;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }
}
