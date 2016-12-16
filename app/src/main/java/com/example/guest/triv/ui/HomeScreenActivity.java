package com.example.guest.triv.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.guest.triv.Constants;
import com.example.guest.triv.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeScreenActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.butt_highscores) Button mHighScoresButton;
    @Bind(R.id.butt_start_game) Button mPlayButton;
    @Bind(R.id.butt_help) Button mHelpButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ButterKnife.bind(this);
        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Logged in as " + user.getDisplayName() + "!");
                    addUserToSharedPreferences(user.getDisplayName());
                }
            }
        };

        mHighScoresButton.setOnClickListener(this);
        mPlayButton.setOnClickListener(this);
        mHelpButton.setOnClickListener(this);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HomeScreenActivity.this, HomeScreenActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }
    private void addUserToSharedPreferences(String location) {
        mEditor.putString(Constants.CURRENT_USER, location).apply();
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(HomeScreenActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v){
        if(v == mHighScoresButton){
            Intent intent = new Intent(HomeScreenActivity.this, HighScoreActivity.class);
            startActivity(intent);
        }
        if(v == mPlayButton){
            Intent intent = new Intent(HomeScreenActivity.this, CategoryActivity.class);
            startActivity(intent);
        }
        if(v == mHelpButton){
            Intent intent = new Intent(HomeScreenActivity.this, InstructionsActivity.class);
            startActivity(intent);
        }
    }
}
