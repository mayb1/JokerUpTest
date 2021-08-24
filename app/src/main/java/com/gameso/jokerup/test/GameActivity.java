package com.gameso.jokerup.test;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Locale;

public class GameActivity extends AppCompatActivity {
    private ImageView imageViewElement;
    private ImageView imageViewLife;
    //private int jokerPic;
    private GameElement elementToSet;
    private boolean elementMoving = true;
    private int screenHeight;
    private GameElement[] elements;
    private int health = 3;
    private int points;
    private Point size;
    private TextView textViewPoints;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        imageViewElement = findViewById(R.id.imageViewElement);
        imageViewLife = findViewById(R.id.imageViewLife);
        textViewPoints = findViewById(R.id.textViewPoints);
        Display display = getWindowManager().getDefaultDisplay();
        size = new Point();
        elementMoving = true;
        display.getSize(size);
        screenHeight = size.y;


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        elements = new GameElement[2];
        elements[0] = new GameElement( "joker", ContextCompat.getDrawable(this, getRandomJokerImageId()));
        elements[1] = new GameElement( "bomb", ContextCompat.getDrawable(this, R.drawable.bomb_icon));
        createNewElement();
        imageViewElement.setOnClickListener(view -> {
            String tag = view.getTag().toString();
            if (tag.equals("joker")) {
                addPoint();
            } else {
                reduceHealth();
            }
            createNewElement();

        });
        startMoving();

    }

    private int getRandomJokerImageId(){
        int resourceId;
       switch((int)  (Math.random()*4) ){
           case 0:
               resourceId = R.drawable.joker_1_icon;
               break;
           case 1:
               resourceId  = R.drawable.joker_2_icon;
               break;
           case 2:
               resourceId = R.drawable.joker_3_icon;
               break;
           case 3:
               resourceId = R.drawable.joker_4_icon;
               break;
           default:
               resourceId = R.drawable.joker_5_icon;
       }
       return resourceId;
    }

    private void addPoint () {
        points++;
        textViewPoints.setText(String.format(Locale.getDefault(), "Points : %d", points));
    }


    private void createNewElement(){
        int newX = (int) (Math.random() * (size.x - imageViewElement.getWidth()));
        elementToSet = elements[(Math.random() > 0.3) ?0 : 1];
        if(elementToSet.getTag().equals("joker")) {
            elementToSet.setDrawable(ContextCompat.getDrawable(this, getRandomJokerImageId()));
        }
        elementToSet.setSpeed(generateSpeed());

        imageViewElement.setImageDrawable(elementToSet.getDrawable());
        imageViewElement.setTag(elementToSet.getTag());

        imageViewElement.setX(newX);
        imageViewElement.setY(0);
    }




    private void reduceHealth(){
        health--;
        switch (health) {
            case 3:
                imageViewLife.setImageResource(R.drawable.life_3_3);
                break;
            case 2:
                imageViewLife.setImageResource(R.drawable.life_2_3);
                break;
            case 1:
                imageViewLife.setImageResource(R.drawable.life_1_3);
                break;
            default:
                elementMoving = false;
                Intent intent = new Intent(this, GameEndActivity.class);
                intent.putExtra("points", points);
                startActivity(intent);
                finish();
                break;
        }
    }

    private int generateSpeed(){
        return (int) (Math.random()*10);
    }


    private void startMoving() {
        Thread thread = new Thread( () -> {
            try {
                while (elementMoving) {
                    if(imageViewElement.getY() >= size.y - (int) (size.y/20)){
                        if(imageViewElement.getTag().toString().equals("joker")){
                            reduceHealth();
                        }

                        runOnUiThread(this::createNewElement);

                    }
                    runOnUiThread(() -> imageViewElement.setY(imageViewElement.getY() + (int) (screenHeight /150) + elementToSet.getSpeed()));
                    Thread.sleep(20);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}




