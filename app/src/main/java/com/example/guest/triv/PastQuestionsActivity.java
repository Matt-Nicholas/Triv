package com.example.guest.triv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PastQuestionsActivity extends AppCompatActivity {


    private ListView mListView;
    private String[] pastQuestions = new String[] {
            "Carcassonne is based on which French town?",
            "What type of animal is a natterjack?",
            "What is the capital of the American state of Arizona?",
            "Just Cause 2 was mainly set in what fictional Southeast Asian island country?",
            };

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pastquestions);

            mListView = (ListView) findViewById(R.id.pastQuestionsListView);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pastQuestions);
        mListView.setAdapter(adapter);

    }
}
