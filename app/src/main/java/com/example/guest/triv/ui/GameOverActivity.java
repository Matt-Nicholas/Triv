package com.example.guest.triv.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(GameOverActivity.this);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setHasFixedSize(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_gameover, menu);
        ButterKnife.bind(this);
        return true;
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GameOverActivity.this, HomeScreenActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_button:
                Intent intent = new Intent(GameOverActivity.this, HomeScreenActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
