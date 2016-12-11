package com.example.guest.triv.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

import static android.os.Build.VERSION_CODES.N;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener{
    // Private variable declarations
    private static final String PREPARING_QUESTIONS = "Preparing your questions!";
    private SharedPreferences.Editor mEditor;
    private String mCategory;
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
        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        mEditor = mSharedPreferences.edit();
        // Set on click listeners
        mSportsButton.setOnClickListener(this);
        mEntertainmentButton.setOnClickListener(this);
        mHistoryButton.setOnClickListener(this);
        mPoliticsButton.setOnClickListener(this);
        mAnimalsButton.setOnClickListener(this);
        mGeographyButton.setOnClickListener(this);
        mRandomButton.setOnClickListener(this);
    }


    private void addCategoryToSharedPreferences(String location) {
        mEditor.putString(Constants.CHOSEN_CATEGORY, location).apply();
    }

    // Execute actions depending on which onClick listener is triggered
    @Override
    public void onClick(View v){
        if(v == mSportsButton){
            mCategory = (String) mSportsButton.getText();
        }
        if(v == mEntertainmentButton){
            mCategory = (String) mEntertainmentButton.getText();
        }
        if(v == mHistoryButton){
            mCategory = (String) mHistoryButton.getText();
        }
        if(v == mPoliticsButton){
            mCategory = (String) mPoliticsButton.getText();
        }
        if(v == mAnimalsButton){
            mCategory = (String) mAnimalsButton.getText();
        }
        if(v == mGeographyButton){
            mCategory = (String) mGeographyButton.getText();
        }
        if(v == mRandomButton){
            mCategory = (String) mRandomButton.getText();
        }
        Toast.makeText(CategoryActivity.this, PREPARING_QUESTIONS, Toast.LENGTH_SHORT).show();
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                addCategoryToSharedPreferences(mCategory);
                Intent intent = new Intent(CategoryActivity.this, QuizActivity.class);
                startActivity(intent);
                finish();
            }

        }, 2000L);

    }
}
