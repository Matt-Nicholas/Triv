package com.example.guest.triv.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.guest.triv.R;
import com.example.guest.triv.adapters.HighScoreListAdapter;
import com.example.guest.triv.models.HighScore;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HighScoreActivity extends AppCompatActivity {
    public static final String TAG = HighScoreActivity.class.getSimpleName();

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private HighScoreListAdapter mAdapter;

    public ArrayList<HighScore> mHighScore = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String highScore = intent.getStringExtra("highScore");

    }

}