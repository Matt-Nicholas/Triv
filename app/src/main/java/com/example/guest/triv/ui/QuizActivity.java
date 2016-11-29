package com.example.guest.triv.ui;

import android.content.Intent;
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
import com.example.guest.triv.models.Question;
import com.example.guest.triv.services.TriviaService;

import java.util.ArrayList;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;

import static android.R.attr.type;

public class QuizActivity extends AppCompatActivity {
    public static final String TAG = QuizActivity.class.getSimpleName();
    // Private variable declarations
    private static final String CORRECT = "Correct!";
    private static final String INCORRECT = "Try Again";
    private ArrayList<String> mPastQuestions = new ArrayList<>();
    private ArrayList<Question> mQuestions = new ArrayList<>();

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
        Intent intent = getIntent();
        String selectedCategory = intent.getStringExtra("category");

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

        getRestaurants();

        // Instantiates a new instance of Question
        setNewQuestion(selectedCategory, type, difficulty, question, correctAnswer, incorrectAnswer);
    }
    private void getRestaurants() {
        final TriviaService triviaService = new TriviaService();
        triviaService.findQuestions(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mQuestions = triviaService.processResults(response);
            }
        });
    }

    public void setNewQuestion(String category, String type, String difficulty, String question, String correctAnswer, ArrayList<String> incorrectAnswer){
        final Question newQuestion = new Question(category, type, difficulty, question, correctAnswer, incorrectAnswer);

        // Creates a new random number generator
        Random randGen = new Random();

        // Sets text for new question views
        mCategoryView.setText(newQuestion.getCategory());
        mQuestionView.setText(newQuestion.getQuestion());

        // Creates a new random int from 0-3
        int randInt = randGen.nextInt(4);

        // ****** NEEDS TO BE REFACTORED NOT DRY ******
        // Sets text on buttons to possible answers
        for (int i = 0; i < 4; i++) {
            if(randInt == 0){
                mAnswerButton0.setText(newQuestion.getCorrectAnswer()); // Sets button text to the correct answer if randInt returns a 0
                mAnswerButton0.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Toast.makeText(QuizActivity.this, CORRECT, Toast.LENGTH_SHORT).show();
//                        PastQuestion newPastQuestion = new PastQuestion(newQuestion, newQuestion.getCorrectAnswer(), true); // Can't pass custom object as Extra?
                        mPastQuestions.add(newQuestion.getQuestion());
                    }
                });
            }else{
                mAnswerButton0.setText(newQuestion.getIncorrectAnswers().get(0)); // Sets button text to incorrect answer if randInt returns something other than 0
                mAnswerButton0.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Toast.makeText(QuizActivity.this, INCORRECT, Toast.LENGTH_SHORT).show();
//                        PastQuestion newPastQuestion = new PastQuestion(newQuestion, newQuestion.getCorrectAnswer(), false); // Can't pass custom object as Extra?
                        mPastQuestions.add(newQuestion.getQuestion());
                        Intent intent = new Intent(QuizActivity.this, PastQuestionsActivity.class);
                        intent.putExtra("pastQuestions", mPastQuestions);
                        startActivity(intent);
                    }
                });
            }
            if(randInt == 1){
                mAnswerButton1.setText(newQuestion.getCorrectAnswer());// Sets button text to the correct answer if randInt returns a 1
                mAnswerButton1.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Toast.makeText(QuizActivity.this, CORRECT, Toast.LENGTH_SHORT).show();
//                        PastQuestion newPastQuestion = new PastQuestion(newQuestion, newQuestion.getCorrectAnswer(), true); // Can't pass custom object as Extra?
                        mPastQuestions.add(newQuestion.getQuestion());
                    }
                });
            }else{
                mAnswerButton1.setText(newQuestion.getIncorrectAnswers().get(1)); // Sets button text to incorrect answer if randInt returns something other than 1
                mAnswerButton1.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Toast.makeText(QuizActivity.this, INCORRECT, Toast.LENGTH_SHORT).show();
//                        PastQuestion newPastQuestion = new PastQuestion(newQuestion, newQuestion.getCorrectAnswer(), false); // Can't pass custom object as Extra?
                        mPastQuestions.add(newQuestion.getQuestion());
                        Intent intent = new Intent(QuizActivity.this, PastQuestionsActivity.class);
                        intent.putExtra("pastQuestions", mPastQuestions);
                        startActivity(intent);
                    }
                });
            }
            if(randInt == 2){
                mAnswerButton2.setText(newQuestion.getCorrectAnswer());// Sets button text to the correct answer if randInt returns a 2
                mAnswerButton2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Toast.makeText(QuizActivity.this, CORRECT, Toast.LENGTH_SHORT).show();
//                        PastQuestion newPastQuestion = new PastQuestion(newQuestion, newQuestion.getCorrectAnswer(), true);// Can't pass custom object as Extra?
                        mPastQuestions.add(newQuestion.getQuestion());
                    }
                });
            }else{
                mAnswerButton2.setText(newQuestion.getIncorrectAnswers().get(2)); // Sets button text to incorrect answer if randInt returns something other than 2
                mAnswerButton2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Toast.makeText(QuizActivity.this, INCORRECT, Toast.LENGTH_SHORT).show();
//                        PastQuestion newPastQuestion = new PastQuestion(newQuestion, newQuestion.getCorrectAnswer(), false);// Can't pass custom object as Extra?
                        mPastQuestions.add(newQuestion.getQuestion());
                        Intent intent = new Intent(QuizActivity.this, PastQuestionsActivity.class);
                        intent.putExtra("pastQuestions", mPastQuestions);
                        startActivity(intent);
                    }
                });
            }
            if(randInt == 3){
                mAnswerButton3.setText(newQuestion.getCorrectAnswer());// Sets button text to the correct answer if randInt returns a 3
                mAnswerButton3.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Toast.makeText(QuizActivity.this, CORRECT, Toast.LENGTH_SHORT).show();
//                        PastQuestion newPastQuestion = new PastQuestion(newQuestion, newQuestion.getCorrectAnswer(), true);// Can't pass custom object as Extra?
                        mPastQuestions.add(newQuestion.getQuestion());
                    }
                });
            }else{
                mAnswerButton3.setText(newQuestion.getIncorrectAnswers().get(3)); // Sets button text to incorrect answer if randInt returns something other than 3
                mAnswerButton3.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Toast.makeText(QuizActivity.this, INCORRECT, Toast.LENGTH_SHORT).show();
//                        PastQuestion newPastQuestion = new PastQuestion(newQuestion, newQuestion.getCorrectAnswer(), false);// Can't pass custom object as Extra?
                        mPastQuestions.add(newQuestion.getQuestion());
                        Intent intent = new Intent(QuizActivity.this, PastQuestionsActivity.class);
                        intent.putExtra("pastQuestions", mPastQuestions);
                        startActivity(intent);
                    }
                });
            }
        }
    }
}
