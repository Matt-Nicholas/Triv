package com.example.guest.triv.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.guest.triv.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                // Starts CategoryActivity after set amount of time

                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);

                startActivity(intent);
            }

        }, 3000L);
    }
}
