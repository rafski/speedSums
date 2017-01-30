package com.example.raf.speedsums;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Collections;

public class GameActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    TextView timerTextView;
    TextView rightWrongText;
    TextView sumTextView;
    TextView scoreTextView;
    Button button4;
    Button button1;
    Button button2;
    Button button3;
    int currentScore;
    int numberOfAttempts;
    int maxGeneratedNumber;
    int generatedRandom;
    int minGeneratedNumber = 1;
    int randomSum;
    List<Integer> randomsList;
    List<Integer> randomSumList;

    public void randomGenerate() {

        maxGeneratedNumber = 30;

        randomsList = new ArrayList<Integer>();

        for (int i = 0; i <= 2; i++) {
            Random r = new Random(maxGeneratedNumber);
            randomsList.add(generatedRandom);
        }
    }

        public int randomSumGenerate(){

            maxGeneratedNumber = 12;

            randomSumList = new ArrayList<Integer>();

            for (int i = 0; i <= 1; i++) {
                Random r = new Random();
                randomSumList.add(generatedRandom);
            }

        sumTextView.setText(randomSumList.get(1) + "+" + randomSumList.get(0));
        randomSum = randomSumList.get(1) + randomSumList.get(0);
        return(randomSum);
    }

    public void assignValueToButton(){

        randomsList.add(3, randomSum);
        Collections.shuffle(randomsList);

        button1.setText(randomsList.get(0).toString());
        button2.setText(randomsList.get(1).toString());
        button3.setText(randomsList.get(2).toString());
        button4.setText(randomsList.get(3).toString());

    }

    public void countScore(){

        //compare scores

        scoreTextView.setText(currentScore + "/" + numberOfAttempts);

    }


    public void selectAnswer(View view) throws InterruptedException {


        if ((String)view.getTag() == "1" && button1.getText() == Integer.toString(randomSum)){
            rightWrongText.setText("Correct!");
        } else if((String)view.getTag() == "2" && button2.getText() == Integer.toString(randomSum)){
            rightWrongText.setText("Correct!");
        } else if ((String)view.getTag() == "3" && button3.getText() == Integer.toString(randomSum)){
            rightWrongText.setText("Correct!");
        } else if((String)view.getTag() == "4" && button4.getText() == Integer.toString(randomSum)){
            rightWrongText.setText("Correct!");
        } else{
            rightWrongText.setText("Wrong!");
        }


                Log.i("Button pressed", (String)view.getTag());
                randomGenerate();
                randomSumGenerate();
                assignValueToButton();
                Log.i("random sum" , Integer.toString(randomSum));



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);

        timerTextView = (TextView)findViewById(R.id.timerTextView);
        rightWrongText = (TextView)findViewById(R.id.rightWrongText);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        scoreTextView = (TextView)findViewById(R.id.scoreTextView);
        randomGenerate();
        randomSumGenerate();
        assignValueToButton();
        scoreTextView.setText("0" + "/" + "0");
        rightWrongText.setText("Good Luck!");



        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

               int seconds = ((int)millisUntilFinished/1000);

                    String secondsString = Integer.toString(seconds);

                    timerTextView.setText(secondsString);

            }

            @Override
            public void onFinish() {

                rightWrongText.setText("Game Over!");

                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Intent myIntent = new Intent(GameActivity.this, MainActivity.class);
                        GameActivity.this.startActivity(myIntent);
                    }
                }, 5000);

            }
        }.start();



    }
}
