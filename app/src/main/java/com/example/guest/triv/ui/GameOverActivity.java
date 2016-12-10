package com.example.guest.triv.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.guest.triv.R;
import com.example.guest.triv.adapters.QuestionListAdapter;
import com.example.guest.triv.models.Game;
import com.example.guest.triv.models.Question;
import org.parceler.Parcels;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.ButterKnife;

public class GameOverActivity extends AppCompatActivity {
    public static final String TAG = GameOverActivity.class.getSimpleName();

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    ArrayList<Question> questions = new ArrayList<>();

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_gameover);
            ButterKnife.bind(this);
            Game game = Parcels.unwrap(getIntent().getParcelableExtra("game"));
            questions = new ArrayList<>(game.getIncorrectlyAnsweredQuestions());
        QuestionListAdapter mAdapter = new QuestionListAdapter(getApplicationContext(), questions);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(GameOverActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
    }
}
