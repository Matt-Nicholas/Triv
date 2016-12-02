package com.example.guest.triv.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.guest.triv.R;
import com.example.guest.triv.adapters.QuestionPagerAdapter;
import com.example.guest.triv.models.Question;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QuestionDetailActivity extends AppCompatActivity {

    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    private QuestionPagerAdapter adapterViewPager;
    ArrayList<Question> mQuestions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);
        ButterKnife.bind(this);

        mQuestions = Parcels.unwrap(getIntent().getParcelableExtra("questions"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new QuestionPagerAdapter(getSupportFragmentManager(), mQuestions);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
