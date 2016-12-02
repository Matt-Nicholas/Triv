package com.example.guest.triv.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.triv.R;
import com.example.guest.triv.models.Question;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 12/2/16.
 */
public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.QuestionViewHolder>{

        private ArrayList<Question> mQuestions = new ArrayList<>();
        private Context mContext;

        public QuestionListAdapter(Context context, ArrayList<Question> questions) {
            mContext = context;
            mQuestions = questions;
        }


    @Override
    public QuestionListAdapter.QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_list_item, parent, false);
        QuestionViewHolder viewHolder = new QuestionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(QuestionListAdapter.QuestionViewHolder holder, int position) {
        holder.bindQuestion(mQuestions.get(position));
    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.categoryTextView) TextView mCategoryTextView;
        @Bind(R.id.questionTextView) TextView mQuestionTextView;
        @Bind(R.id.difficultyTextView) TextView mDifficultyTextView;

        private Context mContext;

        public QuestionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindQuestion(Question question) {
            mCategoryTextView.setText(question.getCategory());
            mQuestionTextView.setText(question.getQuestion());
            mDifficultyTextView.setText(question.getDifficulty());
        }
    }

}
