package com.example.guest.triv.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.guest.triv.R;
import com.example.guest.triv.models.Game;
import com.example.guest.triv.models.Question;

import org.parceler.Parcels;

import java.util.ArrayList;

public class GameOverActivity extends AppCompatActivity {
    ArrayList<Question> questions = new ArrayList<>();

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pastquestions);

        Game game = (Game) Parcels.unwrap(getIntent().getParcelableExtra("game"));


    }

}
