package com.example.guest.triv.ui;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.guest.triv.Constants;
import com.example.guest.triv.R;
import com.example.guest.triv.models.HighScore;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HighScoreActivity extends AppCompatActivity {
    public static final String TAG = HighScoreActivity.class.getSimpleName();
    private DatabaseReference mHighScoreReference;
    Query queryRef;
    @Bind(R.id.lv_high_scores) ListView listView;
    public ArrayList<String> mHighScores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        ButterKnife.bind(this);
        mHighScoreReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_HIGH_SCORES);
        queryRef = mHighScoreReference.orderByChild("score").limitToLast(100);
        queryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    HighScore score=postSnapshot.getValue(HighScore.class);
                    mHighScores.add(score.getUserName() + "   Category:   " + score.getCategory() + "   " + score.getScore());
                }
                Collections.reverse(mHighScores);
                for(int i = 0; i <  mHighScores.size(); i++){
                    String temp = i+1 + " " + mHighScores.get(i);
//                    String padded = String.format("%-20s", temp);
                    mHighScores.set(i, temp);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(HighScoreActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, mHighScores);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}