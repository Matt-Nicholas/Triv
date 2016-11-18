package com.example.guest.triv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity {
    // Private variable declarations
   private static final String UNDER_CONSTRUCTION = "This category is still under construction";

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


        // Set on click listeners and execute actions for category buttons
        mSportsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(CategoryActivity.this, UNDER_CONSTRUCTION, Toast.LENGTH_SHORT).show();
            }
        });
        mEntertainmentButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(CategoryActivity.this, UNDER_CONSTRUCTION, Toast.LENGTH_SHORT).show();
            }
        });
        mHistoryButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(CategoryActivity.this, UNDER_CONSTRUCTION, Toast.LENGTH_SHORT).show();
            }
        });
        mPoliticsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(CategoryActivity.this, UNDER_CONSTRUCTION, Toast.LENGTH_SHORT).show();
            }
        });
        mAnimalsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(CategoryActivity.this, UNDER_CONSTRUCTION, Toast.LENGTH_SHORT).show();
            }
        });
        mGeographyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(CategoryActivity.this, UNDER_CONSTRUCTION, Toast.LENGTH_SHORT).show();
            }
        });
        mRandomButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(CategoryActivity.this, "Random", Toast.LENGTH_LONG).show();
            }
        });

    }
}
