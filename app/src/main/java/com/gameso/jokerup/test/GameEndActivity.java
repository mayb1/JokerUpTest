package com.gameso.jokerup.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.MessageFormat;

public class GameEndActivity extends AppCompatActivity {
    private int points;
    private TextView textViewFinalScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);
        points = getIntent().getIntExtra("points", 0);
        textViewFinalScore = findViewById(R.id.textViewFinalScore);
        textViewFinalScore.setText(MessageFormat.format("Your final score is : {0}", points));

    }

    public void buttonGameClose (View view) {
        finishAffinity();
    }

    public void buttonRestart(View view) {
        Intent intent = new Intent(this , GameActivity.class);
        startActivity(intent);
        finish();

    }
}