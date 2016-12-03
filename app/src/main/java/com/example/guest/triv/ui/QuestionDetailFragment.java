package com.example.guest.triv.ui;


import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.guest.triv.R;
import com.example.guest.triv.models.Question;


import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionDetailFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.categoryTextView) TextView mCategoryLabel;
    @Bind(R.id.difficultyTextView) TextView mDifficultyLabel;
    @Bind(R.id.questionTextView) TextView mQuestionLabel;
    @Bind(R.id.correctAnswerTextView) TextView mCorrectAnswerLabel;
    @Bind(R.id.incorrectAnswerTextView) TextView mIncorrectAnswerLabel;
    @Bind(R.id.learnMoreButton)Button mLearnMoreButton;

    private Question mQuestion;


    public static QuestionDetailFragment newInstance(Question question) {
        QuestionDetailFragment questionDetailFragment = new QuestionDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("question", Parcels.wrap(question));
        questionDetailFragment.setArguments(args);
        return questionDetailFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuestion = Parcels.unwrap(getArguments().getParcelable("question"));

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_detail, container, false);
        ButterKnife.bind(this, view);
        String c = "Category:\n" + mQuestion.getCategory();
        mCategoryLabel.setText(c);
        String difficulty = mQuestion.getDifficulty();
        switch (difficulty){
            case "easy": mDifficultyLabel.setTextColor(0xFF00CC00);
                break;
            case "medium":  mDifficultyLabel.setTextColor(0xFFCCCC00);
                break;
            case "hard":  mDifficultyLabel.setTextColor(0xFFCC0000);
                break;
        }
        difficulty = "Difficulty:\n" + difficulty;
        mDifficultyLabel.setText(difficulty);


        mQuestionLabel.setText(mQuestion.getQuestion());

        mCorrectAnswerLabel.setText(mQuestion.getCorrectAnswer());
        mIncorrectAnswerLabel.setText(mQuestion.getIncorrectGuess());
        mLearnMoreButton.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
        if(v == mLearnMoreButton){
            String url = "https://www.google.com/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=" + mQuestion.getQuestion();
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(url));
            startActivity(webIntent);
        }
    }
}
