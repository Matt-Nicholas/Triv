package com.example.guest.triv.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.guest.triv.Constants;
import com.example.guest.triv.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HighScoreActivity extends AppCompatActivity {
    private DatabaseReference mHighScoreReference;
    private ValueEventListener mHighScoreReferenceListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mHighScoreReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_HIGH_SCORES);

        mHighScoreReferenceListener = mHighScoreReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot highScoreSnapshot : dataSnapshot.getChildren()) {
                    String high_score = highScoreSnapshot.getValue().toString();
                    Log.d("HighScores updated", "high score: " + high_score);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHighScoreReference.removeEventListener(mHighScoreReferenceListener);
    }
}
