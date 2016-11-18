package com.example.guest.triv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QuizActivity extends AppCompatActivity {

    @Bind(R.id.categoryView) TextView mCategoryView;
    @Bind(R.id.questionView) TextView mQuestionView;
    @Bind(R.id.answerButton0) Button mAnswerButton0;
    @Bind(R.id.answerButton1) Button mAnswerButton1;
    @Bind(R.id.answerButton2) Button mAnswerButton2;
    @Bind(R.id.answerButton3) Button mAnswerButton3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);

        // Temporary questions for development only
        String category = "General Knowledge";
        String type = "multiple";
        String difficulty = "easy";
        String question = "What is the Spanish word for \"donkey\"";
        String correctAnswer = "Burro";
        ArrayList<String> incorrectAnswer = new ArrayList<>();

        incorrectAnswer.add("Caballo");
        incorrectAnswer.add("Toro");
        incorrectAnswer.add("Perro");
        incorrectAnswer.add("Burrito");


        Question newQuestion = new Question(category, type, difficulty, question, correctAnswer, incorrectAnswer);

        mCategoryView.setText(newQuestion.getCategory());
        mQuestionView.setText(newQuestion.getQuestion());

        // Creates a new random number generator
        Random randGen = new Random();

        // Creates a new random int from 0-3
        int randInt = randGen.nextInt(4);

        // Sets text on buttons to possible answers
        for (int i = 0; i < 4; i++) {
            if(randInt == 0){
                mAnswerButton0.setText(newQuestion.getCorrectAnswer()); // Sets button text to the correct answer if randInt returns a 0
            }else{
                mAnswerButton0.setText(newQuestion.getIncorrectAnswers().get(0)); // Sets button text to incorrect answer if randInt returns something other than 0
            }
            if(randInt == 1){
                mAnswerButton1.setText(newQuestion.getCorrectAnswer());// Sets button text to the correct answer if randInt returns a 1
            }else{
                mAnswerButton1.setText(newQuestion.getIncorrectAnswers().get(1)); // Sets button text to incorrect answer if randInt returns something other than 1
            }
            if(randInt == 2){
                mAnswerButton2.setText(newQuestion.getCorrectAnswer());// Sets button text to the correct answer if randInt returns a 2
            }else{
                mAnswerButton2.setText(newQuestion.getIncorrectAnswers().get(2)); // Sets button text to incorrect answer if randInt returns something other than 2
            }
            if(randInt == 3){
                mAnswerButton3.setText(newQuestion.getCorrectAnswer());// Sets button text to the correct answer if randInt returns a 3
            }else{
                mAnswerButton3.setText(newQuestion.getIncorrectAnswers().get(3)); // Sets button text to incorrect answer if randInt returns something other than 3
            }
        }
    }
}
