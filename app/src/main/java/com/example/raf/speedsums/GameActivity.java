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

public class GameActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    TextView timerTextView;
    TextView rightWrongText;
    TextView sumTextView;
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button tryAgainButton;
    int currentScore;
    int numberOfAttempts;
    int generatedRandom;
    int randomSum;
    List<Integer> randomsList;
    List<Integer> randomSumList;
    int score = 0;
    int r;
    int randomSumPosition;
    int totalQuestions = 0;

    public void randomGenerate() {

        randomsList = new ArrayList<Integer>();
        Random r = new Random();

        int a = r.nextInt(12);
        int b = r.nextInt(12);
        randomSum = a+b;
        randomsList.add(randomSum);
        sumTextView.setText(a + "+" + b);

        for (int i = 0; i <= 2; i++) {
            generatedRandom = r.nextInt(30);
            if(generatedRandom != randomSum){
                randomsList.add(generatedRandom);
            }
        }
    }

    public void assignValueToButton() {

        button0.setText(randomsList.get(0).toString());
        button1.setText(randomsList.get(1).toString());
        button2.setText(randomsList.get(2).toString());
        button3.setText(randomsList.get(3).toString());


        randomSumPosition = randomsList.indexOf(randomSum);

        Log.i("arrayList" , randomsList.toString());

    }


    public void selectAnswer(View view) {



        Log.i("rs index", Integer.toString(randomSumPosition));
        Log.i("Button pressed", (String)view.getTag());
        Log.i("rs value", Integer.toString(randomSum));

        if (view.getTag().toString().equals(Integer.toString(randomSumPosition))){
            rightWrongText.setText("Correct!");
            score++;
            Log.i("a", "correct");
        } else {
            rightWrongText.setText("Wrong!");
            Log.i("a", "wrong");
        }
        totalQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(totalQuestions));

        randomGenerate();
        assignValueToButton();


    }

    public void tryAgain (View view){

        Intent myIntent = new Intent(GameActivity.this, MainActivity.class);
        GameActivity.this.startActivity(myIntent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);

        tryAgainButton = (Button)findViewById(R.id.tryAgainButton);

        timerTextView = (TextView)findViewById(R.id.timerTextView);
        rightWrongText = (TextView)findViewById(R.id.rightWrongText);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        scoreTextView = (TextView)findViewById(R.id.scoreTextView);

        scoreTextView.setText("0" + "/" + "1");
        rightWrongText.setText("Good Luck!");
        tryAgainButton.setVisibility(View.INVISIBLE);

        button0.setClickable(true);
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);


        randomGenerate();
        assignValueToButton();

        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

               int seconds = ((int)millisUntilFinished/1000);

                    String secondsString = Integer.toString(seconds);

                    timerTextView.setText(secondsString);

            }

            @Override
            public void onFinish() {

                timerTextView.setText("0");

                rightWrongText.setText(scoreTextView.getText());

                tryAgainButton.setVisibility(View.VISIBLE);

                button0.setClickable(false);
                button1.setClickable(false);
                button2.setClickable(false);
                button3.setClickable(false);

            }
        }.start();



    }
}
