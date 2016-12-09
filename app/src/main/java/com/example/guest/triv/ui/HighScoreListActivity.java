package com.example.guest.triv.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.guest.triv.Constants;
import com.example.guest.triv.R;
import com.example.guest.triv.adapters.FirebaseHighScoreViewHolder;
import com.example.guest.triv.models.HighScore;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HighScoreListActivity extends AppCompatActivity {


        private DatabaseReference mHighScoreReference;
        private FirebaseRecyclerAdapter mFirebaseAdapter;

        @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_high_score);
            ButterKnife.bind(this);

            mHighScoreReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_HIGH_SCORES);
            setUpFirebaseAdapter();
        }

        private void setUpFirebaseAdapter() {
            mFirebaseAdapter = new FirebaseRecyclerAdapter<HighScore, FirebaseHighScoreViewHolder>
                    (HighScore.class, R.layout.high_score_list_item, FirebaseHighScoreViewHolder.class, mHighScoreReference) {

                @Override
                protected void populateViewHolder(FirebaseHighScoreViewHolder viewHolder,HighScore model, int position) {

                    Log.d("MATT ** model", model.getUserName());
                    viewHolder.bindHighScore(model);
                }
            };
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(mFirebaseAdapter);
        }

        @Override
        protected void onDestroy() {
            Log.d("MATT ** ", " DESTROYED");
            super.onDestroy();
            mFirebaseAdapter.cleanup();
        }
    }

