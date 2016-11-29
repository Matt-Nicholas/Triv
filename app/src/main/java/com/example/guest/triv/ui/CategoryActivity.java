package com.example.guest.triv.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.guest.triv.R;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.R.attr.category;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener{
    // Private variable declarations
    private static final String UNDER_CONSTRUCTION = "This category is still under construction";
    private static final String PREPARING_QUESTIONS = "Preparing your questions!";
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

        // Set on click listeners
        mSportsButton.setOnClickListener(this);
        mEntertainmentButton.setOnClickListener(this);
        mHistoryButton.setOnClickListener(this);
        mPoliticsButton.setOnClickListener(this);
        mAnimalsButton.setOnClickListener(this);
        mGeographyButton.setOnClickListener(this);
        mRandomButton.setOnClickListener(this);
    }
    // Execute actions depending on which onClick listener is triggered
    @Override
    public void onClick(View v){
        if(v == mSportsButton){
            Toast.makeText(CategoryActivity.this, UNDER_CONSTRUCTION, Toast.LENGTH_SHORT).show();
        }
        if(v == mEntertainmentButton){
            Toast.makeText(CategoryActivity.this, UNDER_CONSTRUCTION, Toast.LENGTH_SHORT).show();
        }
        if(v == mHistoryButton){
            Toast.makeText(CategoryActivity.this, UNDER_CONSTRUCTION, Toast.LENGTH_SHORT).show();
        }
        if(v == mPoliticsButton){
            Toast.makeText(CategoryActivity.this, UNDER_CONSTRUCTION, Toast.LENGTH_SHORT).show();
        }
        if(v == mAnimalsButton){
            Toast.makeText(CategoryActivity.this, UNDER_CONSTRUCTION, Toast.LENGTH_SHORT).show();
        }
        if(v == mGeographyButton){
            Toast.makeText(CategoryActivity.this, UNDER_CONSTRUCTION, Toast.LENGTH_SHORT).show();
        }
        if(v == mRandomButton){
            Toast.makeText(CategoryActivity.this, PREPARING_QUESTIONS, Toast.LENGTH_SHORT).show();

            Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    // Starts CategoryActivity after set amount of time
                    // Will eventually start after api call is complete
                    String category = (String) mRandomButton.getText();
                    Intent intent = new Intent(CategoryActivity.this, QuizActivity.class);
                    intent.putExtra("category", category);
                    startActivity(intent);
                }

            }, 2000L);
        }

    }
}
