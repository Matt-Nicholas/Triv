package com.example.guest.triv.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.triv.Constants;
import com.example.guest.triv.R;
import com.example.guest.triv.models.Game;
import com.example.guest.triv.models.HighScore;
import com.example.guest.triv.models.Question;
import com.example.guest.triv.services.TriviaService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = QuizActivity.class.getSimpleName();
    // Private variable declarations
    private ArrayList<Question> mQuestions = new ArrayList<>();
    List<String> allAnswers = new ArrayList<>();
    private Game game;
    private String mUser;
    //Bind views using ButtKnife
    @Bind(R.id.categoryView) TextView mCategoryView;
    @Bind(R.id.questionView) TextView mQuestionView;
    @Bind(R.id.answerButton0) Button mAnswerButton0;
    @Bind(R.id.answerButton1) Button mAnswerButton1;
    @Bind(R.id.answerButton2) Button mAnswerButton2;
    @Bind(R.id.answerButton3) Button mAnswerButton3;
    @Bind(R.id.tv_coin_count) TextView mCoinCount;
    @Bind(R.id.tv_score) TextView mCurrentScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);
        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String mCategory = mSharedPreferences.getString(Constants.CHOSEN_CATEGORY, null);
        mUser = mSharedPreferences.getString(Constants.CURRENT_USER, null);
        // Set on click listeners
        mAnswerButton0.setOnClickListener(this);
        mAnswerButton1.setOnClickListener(this);
        mAnswerButton2.setOnClickListener(this);
        mAnswerButton3.setOnClickListener(this);
        toggleButtonClickable(false);//Disable click listener for all buttons until new question is loaded
        game = new Game(mCategory);
        if(!mCategory.equals("RANDOM")){
            game.setCategoryId(mCategory);
        }
        getQuestions();
    }
    @Override
    public void onBackPressed() {
        // TODO: 12/10/2016 back button prompts user to verify quit game
    }

      // Execute actions depending on which onClick listener is triggered
    @Override
    public void onClick(View v){
        String selectedAnswer;
        //Disable click listener for all buttons until new question is loaded
        toggleButtonClickable(false);
        if(v == mAnswerButton0){
            selectedAnswer = (String) mAnswerButton0.getText();
            if(answerIsCorrect(selectedAnswer)){ // CORRECT ANSWER
                game.correctAnswer(mQuestions.get(0).getDifficulty());// Adds one to streak for a correct answer
                mAnswerButton0.setBackgroundColor(0xff00ff00); // sets button color to green to show correct answer
            }else{ // INCORRECT ANSWER
                mQuestions.get(0).setIncorrectGuess(mAnswerButton0.getText().toString());
                game.incorrectAnswer(mQuestions.get(0));// Adds the current question to incorrectly answered questions and Sets streak back to zero
                mAnswerButton0.setBackgroundColor(0xffff0000); // Sets button color to red to show incorrect answer
            }
        } else if(v == mAnswerButton1){
            selectedAnswer = (String) mAnswerButton1.getText();
            if(answerIsCorrect(selectedAnswer)){ // CORRECT ANSWER
                game.correctAnswer(mQuestions.get(0).getDifficulty());
                mAnswerButton1.setBackgroundColor(0xff00ff00);
            }else{ // INCORRECT ANSWER
                mQuestions.get(0).setIncorrectGuess(mAnswerButton1.getText().toString());
                game.incorrectAnswer(mQuestions.get(0));
                mAnswerButton1.setBackgroundColor(0xffff0000);
            }
        }else if(v == mAnswerButton2){
            selectedAnswer = (String) mAnswerButton2.getText();
            if(answerIsCorrect(selectedAnswer)){ // CORRECT ANSWER
                game.correctAnswer(mQuestions.get(0).getDifficulty());
                mAnswerButton2.setBackgroundColor(0xff00ff00);
            }else{ // INCORRECT ANSWER
                mQuestions.get(0).setIncorrectGuess(mAnswerButton2.getText().toString());
                game.incorrectAnswer(mQuestions.get(0));
                mAnswerButton2.setBackgroundColor(0xffff0000);
            }
        }else if(v == mAnswerButton3){
            selectedAnswer = (String) mAnswerButton3.getText();
            if(answerIsCorrect(selectedAnswer)){ // CORRECT ANSWER
                game.correctAnswer(mQuestions.get(0).getDifficulty());
                mAnswerButton3.setBackgroundColor(0xff00ff00);
            }else{ // INCORRECT ANSWER
                mQuestions.get(0).setIncorrectGuess(mAnswerButton3.getText().toString());
                game.incorrectAnswer(mQuestions.get(0));
                mAnswerButton3.setBackgroundColor(0xffff0000);
            }
        }
        if(game.getNumOfCoins() <= 4){  // GAME OVER
            saveScore();
            Intent intent = new Intent(QuizActivity.this, GameOverActivity.class);
            intent.putExtra("game", Parcels.wrap(game)); // Passes current game to the game over activity
            startActivity(intent);
        }else{ // STILL PLAYING
            Log.d("MATT SCORE ****  ", Integer.toString(game.getScore()));
            Log.d("MATT STREAK ****  ", Integer.toString(game.getCorrectAnswerStreak()));
            Log.d("MATT COINS ****  ", Integer.toString(game.getNumOfCoins()));
            getQuestions(); // Gets a new question from the api and replaces the text views with the new info
        }
    }
    public boolean answerIsCorrect(String a){
        if (mQuestions.get(0).getCorrectAnswer().equals(a)){
            return true;
        }
        return false;
    }

    private void getQuestions() {
        final TriviaService triviaService = new TriviaService();
        triviaService.findQuestions(game.getCategoryId(), new Callback() {
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
//
    //Create HighScore object and save it to firebase
    public void saveScore(){
        DatabaseReference mHighScoreReference;
        HighScore hs= new HighScore(game.getScore(), mUser, game.getCategory());
        mHighScoreReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_HIGH_SCORES);
        mHighScoreReference.push().setValue(hs);
        Toast.makeText(QuizActivity.this, "New High Score!", Toast.LENGTH_SHORT).show();
    }

    // UPDATE DISPLAY
    public void setNewQuestion(String category, String type, String difficulty, String question, String correctAnswer, ArrayList<String> incorrectAnswer){
        String coinDisplay = "x " + game.getNumOfCoins();
        mCoinCount.setText(coinDisplay);
        mCurrentScore.setText(Integer.toString(game.getScore()));
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

        // Sets clickable back to true
        toggleButtonClickable(true);
    }
    // Toggles click on buttons
    public void toggleButtonClickable(boolean b){
        mAnswerButton0.setClickable(b);
        mAnswerButton1.setClickable(b);
        mAnswerButton2.setClickable(b);
        mAnswerButton3.setClickable(b);
    }
}


