package com.example.guest.triv.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.guest.triv.R;
import com.example.guest.triv.models.HighScore;


/**
 * Created by Guest on 12/9/16.
 */
public class FirebaseHighScoreViewHolder extends RecyclerView.ViewHolder{
    View mView;
    Context mContext;

    public FirebaseHighScoreViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindHighScore(HighScore highScore) {
        TextView userTextView = (TextView) mView.findViewById(R.id.tv_hs_username);
        TextView categoryTextView = (TextView) mView.findViewById(R.id.tv_hs_category);
        TextView scoreTextView = (TextView) mView.findViewById(R.id.tv_hs_score);

        userTextView.setText(highScore.getUserName());
        categoryTextView.setText(highScore.getCategory());
        scoreTextView.setText(Integer.toString(highScore.getScore()));
    }
}
