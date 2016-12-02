package com.example.guest.triv.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.guest.triv.models.Question;
import com.example.guest.triv.ui.QuestionDetailFragment;

import java.util.ArrayList;

public class QuestionPagerAdapter extends FragmentPagerAdapter{
    private ArrayList<Question> mQuestions;

    public QuestionPagerAdapter(FragmentManager fm, ArrayList<Question> questions) {
        super(fm);
        mQuestions = questions;
    }

    @Override
    public Fragment getItem(int position) {
        return QuestionDetailFragment.newInstance(mQuestions.get(position));
    }

    @Override
    public int getCount() {
        return mQuestions.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mQuestions.get(position).getCategory();
    }
}
