package com.example.guest.triv.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.triv.Constants;
import com.example.guest.triv.R;
import com.example.guest.triv.models.Game;
import com.example.guest.triv.models.HighScore;
import com.example.guest.triv.models.Question;
import com.example.guest.triv.services.TriviaService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
    private Game game;
    private String mUser;
    private boolean mTimerExists = false;
    int timer;
    Query queryRef;
    public ArrayList<Integer> mHighScores = new ArrayList<>();
    List<String> allAnswers = new ArrayList<>();
    private DatabaseReference mHighScoreReference;
    ProgressBar mProgressBar;
    CountDownTimer mCountDownTimer;
    private int tempScore;

    //Bind views using ButtKnife
    @Bind(R.id.categoryView) TextView mCategoryView;
    @Bind(R.id.answerButton0) Button mAnswerButton0;
    @Bind(R.id.answerButton1) Button mAnswerButton1;
    @Bind(R.id.answerButton2) Button mAnswerButton2;
    @Bind(R.id.answerButton3) Button mAnswerButton3;
    @Bind(R.id.tv_coin_count) TextView mCoinCount;
    @Bind(R.id.tv_score) TextView mCurrentScore;
    @Bind(R.id.iv_coin) ImageView mCoin;
    @Bind(R.id.text_switcher) TextSwitcher textSwitcher;

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

        textSwitcher.setInAnimation(this, R.anim.slide_in_left);
        textSwitcher.setOutAnimation(this, R.anim.slide_out_left);

        textSwitcher.addView(new TextView(this));
        textSwitcher.addView(new TextView(this));
    }

    public void qTimer(){
        timer = 200;
        mProgressBar=(ProgressBar)findViewById(R.id.pb_question_timer);
        mProgressBar.setProgress(timer);

        mCountDownTimer=new CountDownTimer(20000,100) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer--;
                mProgressBar.setProgress(timer);
                if(timer > 100){
                    mProgressBar.getProgressDrawable().setColorFilter(
                            Color.GREEN, android.graphics.PorterDuff.Mode.MULTIPLY);
                }else if( timer > 50){
                    mProgressBar.getProgressDrawable().setColorFilter(
                            Color.YELLOW, android.graphics.PorterDuff.Mode.MULTIPLY);
                }else{
                    mProgressBar.getProgressDrawable().setColorFilter(
                            Color.RED, android.graphics.PorterDuff.Mode.MULTIPLY);
                }
            }

            @Override
            public void onFinish() {
                if(game.getNumOfCoins() > 0){
                    mQuestions.get(0).setIncorrectGuess("Ran out of time");
                    game.incorrectAnswer(mQuestions.get(0));// Adds the current question to incorrectly answered questions and Sets streak back to zero
                    checkIfStillPlaying();
                }
            }
        };
        mTimerExists = true;
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to quit? All progress will be lost")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(QuizActivity.this, HomeScreenActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

      // Execute actions depending on which onClick listener is triggered
    @Override
    public void onClick(View v){
        mCountDownTimer.cancel();
        String selectedAnswer;
        //Disable click listener for all buttons until new question is loaded
        toggleButtonClickable(false);
        if(v == mAnswerButton0){
            selectedAnswer = (String) mAnswerButton0.getText();
            if(answerIsCorrect(selectedAnswer)){ // CORRECT ANSWER
                game.correctAnswer(mQuestions.get(0).getDifficulty());// Adds one to streak for a correct answer
                mAnswerButton0.setBackgroundColor(0xff00ff00); // sets button color to green to show correct answer
                playCoinFlip(); // Plays when player has earned a coin
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
                playCoinFlip();
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
                playCoinFlip();
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
                playCoinFlip();
            }else{ // INCORRECT ANSWER
                mQuestions.get(0).setIncorrectGuess(mAnswerButton3.getText().toString());
                game.incorrectAnswer(mQuestions.get(0));
                mAnswerButton3.setBackgroundColor(0xffff0000);
            }
        }
        checkIfStillPlaying();
    }

    public boolean answerIsCorrect(String a){
        if (mQuestions.get(0).getCorrectAnswer().equals(a)){
            return true;
        }
        return false;
    }

    public void checkIfStillPlaying(){
        if(game.getNumOfCoins() <= 0){  // GAME OVER
            checkForNewHighScore();
            if(game.getScore() > tempScore){
                saveScore();
                Toast.makeText(QuizActivity.this, "New High Score!", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(QuizActivity.this, GameOverActivity.class);
            intent.putExtra("game", Parcels.wrap(game)); // Passes current game to the game over activity
            startActivity(intent);
            finish();
        }else{ // STILL PLAYING
            getQuestions(); // Gets a new question from the api and replaces the text views with the new info
        }
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

    // Check firebase to see if score is a new high score
    public void checkForNewHighScore(){
        int temp;
        mHighScoreReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_HIGH_SCORES);
        queryRef = mHighScoreReference.orderByChild("score").limitToLast(100);
        queryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    HighScore score = postSnapshot.getValue(HighScore.class);
                    mHighScores.add(score.getScore());
                }
                tempScore = mHighScores.get(0);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    //Create HighScore object and save it to firebase
    public void saveScore(){
        DatabaseReference mHighScoreReference;
        HighScore hs= new HighScore(game.getScore(), mUser, game.getCategory());
        mHighScoreReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_HIGH_SCORES);
        mHighScoreReference.push().setValue(hs);
    }

    // UPDATE DISPLAY
    public void setNewQuestion(String category, String type, String difficulty, final String question, String correctAnswer, ArrayList<String> incorrectAnswer){
        if(mTimerExists){
            mCountDownTimer.cancel();
        }
        qTimer();

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

        final String mQuestion = newQuestion.getQuestion();


        textSwitcher.setText(mQuestion);

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
        mCountDownTimer.start();

    }
    // Toggles click on buttons
    public void toggleButtonClickable(boolean b){
        mAnswerButton0.setClickable(b);
        mAnswerButton1.setClickable(b);
        mAnswerButton2.setClickable(b);
        mAnswerButton3.setClickable(b);
    }
    // Plays a spin animation on the coin image
    public void playCoinFlip(){
        if(game.getCorrectAnswerStreak() % 5 == 0){
            // Set spinning coin animation to mCoin Image View
            mCoin.setImageDrawable(getResources().getDrawable(R.drawable.animation_coin));
            AnimationDrawable coinAnimation = (AnimationDrawable) mCoin.getDrawable();
            coinAnimation.start();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mCountDownTimer.cancel();
        finish();
    }
}


