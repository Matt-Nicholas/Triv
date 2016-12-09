package com.example.guest.triv.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guest.triv.R;
import com.example.guest.triv.models.HighScore;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 12/9/16.
 */
public class HighScoreListAdapter extends RecyclerView.Adapter<HighScoreListAdapter.HighScoreViewHolder> {
    private ArrayList<HighScore> mHighScores = new ArrayList<>();
    private Context mContext;

    public HighScoreListAdapter(Context context, ArrayList<HighScore> highScore) {
        mContext = context;
        mHighScores = highScore;
    }
    public class HighScoreViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_hs_username) TextView mUserNameTextView;
        @Bind(R.id.tv_hs_category) TextView mCategoryTextView;
        @Bind(R.id.tv_hs_score) TextView mScoreTextView;

        private Context mContext;

        public HighScoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindHighScore(HighScore highScore) {
            mUserNameTextView.setText(highScore.getUserName());
            mCategoryTextView.setText(highScore.getCategory());
            mScoreTextView.setText(highScore.getScore());
        }
    }

    @Override
    public HighScoreListAdapter.HighScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.high_score_list_item, parent, false);
        HighScoreViewHolder viewHolder = new HighScoreViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HighScoreListAdapter.HighScoreViewHolder holder, int position) {
        holder.bindHighScore(mHighScores.get(position));
    }

    @Override
    public int getItemCount() {
        return mHighScores.size();
    }

}