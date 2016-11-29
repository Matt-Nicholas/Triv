package com.example.guest.triv.models;

import com.example.guest.triv.models.Question;

/**
 * Created by Guest on 11/18/16.
 */
public class PastQuestion {
    private Question mQuestion;
    private boolean mCorrect;
    private String mAnswer;

    public PastQuestion(Question question,String answer, boolean correct){
        this.mQuestion = question;
        this.mCorrect = correct;
        this.mAnswer = answer;
    }

    public Question getSubmittedQuestion(){
        return mQuestion;
    }
    public boolean getCorrect(){
        return mCorrect;
    }
    public String getPastAnswer(){
        return mAnswer;
    }
}
