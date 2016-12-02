package com.example.guest.triv.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import okhttp3.Call;
import okhttp3.Callback;
import java.io.IOException;
import com.example.guest.triv.R;
import com.example.guest.triv.models.PastQuestion;
import com.example.guest.triv.models.Question;
import com.example.guest.triv.services.TriviaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = QuizActivity.class.getSimpleName();
    // Private variable declarations
    private static final String CORRECT = "Correct!";
    private static final String INCORRECT = "Wrong!";
    private ArrayList<String> mPastQuestions = new ArrayList<>();
    private ArrayList<Question> mQuestions = new ArrayList<>();
    List<String> allAnswers = new ArrayList<>();

    //Bind views using ButtKnife
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

        // Set on click listeners
        mAnswerButton0.setOnClickListener(this);
        mAnswerButton1.setOnClickListener(this);
        mAnswerButton2.setOnClickListener(this);
        mAnswerButton3.setOnClickListener(this);

//        Intent intent = getIntent();
//        String selectedCategory = intent.getStringExtra("category");
        getQuestions();
    }
      // Execute actions depending on which onClick listener is triggered
    @Override
    public void onClick(View v){
        String selectedAnswer;
        if(v == mAnswerButton0){
            selectedAnswer = (String) mAnswerButton0.getText();
            if(answerIsCorrect(selectedAnswer, 0)){
                Toast.makeText(QuizActivity.this, CORRECT, Toast.LENGTH_SHORT).show();
                mAnswerButton0.setBackgroundColor(0xff00ff00);
                getQuestions();
            }else{
                getQuestions();
                Toast.makeText(QuizActivity.this, INCORRECT, Toast.LENGTH_SHORT).show();
                mAnswerButton0.setBackgroundColor(0xffff0000);
            }
        } else if(v == mAnswerButton1){
            selectedAnswer = (String) mAnswerButton1.getText();
            if(answerIsCorrect(selectedAnswer, 1)){
                Toast.makeText(QuizActivity.this, CORRECT, Toast.LENGTH_SHORT).show();
                mAnswerButton1.setBackgroundColor(0xff00ff00);
                getQuestions();
            }else{
                getQuestions();
                Toast.makeText(QuizActivity.this, INCORRECT, Toast.LENGTH_SHORT).show();
                mAnswerButton1.setBackgroundColor(0xffff0000);
            }
        }else if(v == mAnswerButton2){
            selectedAnswer = (String) mAnswerButton2.getText();
            if(answerIsCorrect(selectedAnswer, 2)){
                Toast.makeText(QuizActivity.this, CORRECT, Toast.LENGTH_SHORT).show();
                mAnswerButton2.setBackgroundColor(0xff00ff00);
                getQuestions();
            }else{
                getQuestions();
                Toast.makeText(QuizActivity.this, INCORRECT, Toast.LENGTH_SHORT).show();
                mAnswerButton2.setBackgroundColor(0xffff0000);
            }
        }else if(v == mAnswerButton3){
            selectedAnswer = (String) mAnswerButton3.getText();
            if(answerIsCorrect(selectedAnswer, 3)){
                Toast.makeText(QuizActivity.this, CORRECT, Toast.LENGTH_SHORT).show();
                mAnswerButton3.setBackgroundColor(0xff00ff00);
                getQuestions();
            }else{
                getQuestions();
                Toast.makeText(QuizActivity.this, INCORRECT, Toast.LENGTH_SHORT).show();
                mAnswerButton3.setBackgroundColor(0xffff0000);
            }
        }
    }
    public boolean answerIsCorrect(String a, int i){
        if (allAnswers.get(i).equals(a)){
            return true;
        }
        return false;
    }

    private void getQuestions() {
        final TriviaService triviaService = new TriviaService();
        triviaService.findQuestions(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) {
                mQuestions = triviaService.processResults(response);
               QuizActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setNewQuestion(mQuestions.get(0).getCategory(), mQuestions.get(0).getType(), mQuestions.get(0).getDifficulty(), mQuestions.get(0).getQuestion(), mQuestions.get(0).getCorrectAnswer(), mQuestions.get(0).getIncorrectAnswers());
                    }
                });
            }
        });
    }

    public void setNewQuestion(String category, String type, String difficulty, String question, String correctAnswer, ArrayList<String> incorrectAnswer){

        // Make a copy of incorrect Answers array list
        allAnswers = new ArrayList<>(incorrectAnswer);
        final Question newQuestion = new Question(category, type, difficulty, question, correctAnswer, incorrectAnswer);

        // Creates a new random number generator
        Random randGen = new Random();

        // Sets text for new question views
        mCategoryView.setText(newQuestion.getCategory());
        mQuestionView.setText(newQuestion.getQuestion());

        // Creates a new random int from 0-3
        int randInt = randGen.nextInt(4);

        // reset background color to white on all buttons
        allAnswers.add(randInt, correctAnswer);
        mAnswerButton0.setBackgroundColor(0xffffffff);
        mAnswerButton1.setBackgroundColor(0xffffffff);
        mAnswerButton2.setBackgroundColor(0xffffffff);
        mAnswerButton3.setBackgroundColor(0xffffffff);

        // Sets text on buttons to possible answers
        mAnswerButton0.setText(allAnswers.get(0));
        mAnswerButton1.setText(allAnswers.get(1));
        mAnswerButton2.setText(allAnswers.get(2));
        mAnswerButton3.setText(allAnswers.get(3));
    }
}


//    PastQuestion newPastQuestion = new PastQuestion(newQuestion, newQuestion.getCorrectAnswer(), false); // Can't pass custom object as Extra?
//                        mPastQuestions.add(newQuestion.getQuestion());
//                        Intent intent = new Intent(QuizActivity.this, PastQuestionsActivity.class);
//                        intent.putExtra("pastQuestions", mPastQuestions);
//                        startActivity(intent);