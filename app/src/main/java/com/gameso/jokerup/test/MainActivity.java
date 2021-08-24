package com.gameso.jokerup.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void buttonStart(View view) {
        Intent intent = new Intent(this , LoadingActivity.class);
        startActivity(intent);
        finish();

    }

    public void buttonQuit(View view) {
        finishAffinity();
    }
}
