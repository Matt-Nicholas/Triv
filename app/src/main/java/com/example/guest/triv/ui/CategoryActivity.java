package com.example.guest.triv.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.guest.triv.Constants;
import com.example.guest.triv.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener{
    // Private variable declarations
    private static final String UNDER_CONSTRUCTION = "This category is still under construction";
    private static final String PREPARING_QUESTIONS = "Preparing your questions!";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    //Bind views using ButtKnife
    @Bind(R.id.sportsButton) Button mSportsButton;
    @Bind(R.id.entertainmentButton) Button mEntertainmentButton;
    @Bind(R.id.historyButton) Button mHistoryButton;
    @Bind(R.id.politicsButton) Button mPoliticsButton;
    @Bind(R.id.animalsButton) Button mAnimalsButton;
    @Bind(R.id.geographyButton) Button mGeographyButton;
    @Bind(R.id.randomButton) Button mRandomButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                } else {
                }
            }
        };

        // Set on click listeners
        mSportsButton.setOnClickListener(this);
        mEntertainmentButton.setOnClickListener(this);
        mHistoryButton.setOnClickListener(this);
        mPoliticsButton.setOnClickListener(this);
        mAnimalsButton.setOnClickListener(this);
        mGeographyButton.setOnClickListener(this);
        mRandomButton.setOnClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(CategoryActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.CHOOSEN_CATEGORY, location).apply();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    // Execute actions depending on which onClick listener is triggered
    @Override
    public void onClick(View v){
        if(v == mSportsButton){
            Toast.makeText(CategoryActivity.this, PREPARING_QUESTIONS, Toast.LENGTH_SHORT).show();
            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    String category = (String) mSportsButton.getText();
                    addToSharedPreferences(category);
                    Intent intent = new Intent(CategoryActivity.this, QuizActivity.class);
                    startActivity(intent);
                }

            }, 2000L);        }
        if(v == mEntertainmentButton){
            Toast.makeText(CategoryActivity.this, PREPARING_QUESTIONS, Toast.LENGTH_SHORT).show();
            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    String category = (String) mEntertainmentButton.getText();
                    addToSharedPreferences(category);

                    Intent intent = new Intent(CategoryActivity.this, QuizActivity.class);
                    startActivity(intent);
                }

            }, 2000L);        }
        if(v == mHistoryButton){
            Toast.makeText(CategoryActivity.this, PREPARING_QUESTIONS, Toast.LENGTH_SHORT).show();
            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    String category = (String) mHistoryButton.getText();
                    addToSharedPreferences(category);

                    Intent intent = new Intent(CategoryActivity.this, QuizActivity.class);
                    startActivity(intent);
                }

            }, 2000L);        }
        if(v == mPoliticsButton){
            Toast.makeText(CategoryActivity.this, PREPARING_QUESTIONS, Toast.LENGTH_SHORT).show();
            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    String category = (String) mPoliticsButton.getText();
                    addToSharedPreferences(category);

                    Intent intent = new Intent(CategoryActivity.this, QuizActivity.class);
                    startActivity(intent);
                }

            }, 2000L);        }
        if(v == mAnimalsButton){
            Toast.makeText(CategoryActivity.this, PREPARING_QUESTIONS, Toast.LENGTH_SHORT).show();
            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    String category = (String) mAnimalsButton.getText();
                    addToSharedPreferences(category);

                    Intent intent = new Intent(CategoryActivity.this, QuizActivity.class);
                    startActivity(intent);
                }

            }, 2000L);        }
        if(v == mGeographyButton){
            Toast.makeText(CategoryActivity.this, PREPARING_QUESTIONS, Toast.LENGTH_SHORT).show();
            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    String category = (String) mGeographyButton.getText();
                    addToSharedPreferences(category);

                    Intent intent = new Intent(CategoryActivity.this, QuizActivity.class);
                    startActivity(intent);
                }

            }, 2000L);        }
        if(v == mRandomButton){
            Toast.makeText(CategoryActivity.this, PREPARING_QUESTIONS, Toast.LENGTH_SHORT).show();
            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    String category = (String) mRandomButton.getText();
                    addToSharedPreferences(category);

                    Intent intent = new Intent(CategoryActivity.this, QuizActivity.class);
                    startActivity(intent);
                }

            }, 2000L);
        }

    }
}
