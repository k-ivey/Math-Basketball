package com.example.mathbasketball;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.*;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setQuestion();

        Button clickButtonOne = findViewById(R.id.buttonTopLeft);
        Button clickButtonTwo = findViewById(R.id.buttonTopRight);
        Button clickButtonThree = findViewById(R.id.buttonBotLeft);
        Button clickButtonFour = findViewById(R.id.buttonBotRight);

        clickButtonOne.setOnClickListener(answerClicker);
        clickButtonTwo.setOnClickListener(answerClicker);
        clickButtonThree.setOnClickListener(answerClicker);
        clickButtonFour.setOnClickListener(answerClicker);
    }

    private View.OnClickListener answerClicker = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                default:
                    Button clickedButton = (Button) view;
                    String clickedAnswer = clickedButton.getText().toString();

                    if (clickedAnswer.equals(answer.toString())) {
                        increaseScore(view);
                        //setQuestion();
                        startBasketball(view);
                    }
                    else {
                        Toast incorrectMessage = Toast.makeText(getApplicationContext(),
                                "The correct answer is " + answer, Toast.LENGTH_SHORT);
                        incorrectMessage.setGravity(0,0,20);
                        incorrectMessage.show();

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Change question after 2.2s to match toast length
                                setQuestion();
                            }
                        }, 2200);
                    }
            }
        }
    };

    public void increaseScore(View view) {

        TextView showScoreTextView = findViewById(R.id.textScore);
        String scoreString = showScoreTextView.getText().toString();
        Integer score = Integer.parseInt(scoreString);
        score += 1;

        showScoreTextView.setText(String.format(Locale.ENGLISH, "%d", score));

        }

    public Integer answer = 0;

    public void setQuestion() {
        Random rand = new Random();

        TextView buttonOne = findViewById(R.id.buttonTopLeft);
        TextView buttonTwo = findViewById(R.id.buttonTopRight);
        TextView buttonThree = findViewById(R.id.buttonBotLeft);
        TextView buttonFour = findViewById(R.id.buttonBotRight);
        TextView question = findViewById(R.id.textQuestion);

        Integer numberOne = rand.nextInt(101);
        Integer numberTwo = rand.nextInt(101);
        answer = numberOne + numberTwo;
        Integer wrongOne = answer - rand.nextInt(3) - 1;
        Integer wrongTwo = answer + rand.nextInt(3) + 1;
        Integer wrongThree = answer;
        while (wrongThree.equals(answer) || wrongThree.equals(wrongTwo) || wrongThree.equals(wrongOne)) {
            wrongThree = answer + rand.nextInt(10) - rand.nextInt(10);
        }

        List<Integer> choices = new ArrayList<>(Arrays.asList(answer, wrongOne, wrongTwo, wrongThree));
        Collections.shuffle(choices);

        buttonOne.setText(String.format(Locale.ENGLISH, "%d", choices.get(0)));
        buttonTwo.setText(String.format(Locale.ENGLISH, "%d", choices.get(1)));
        buttonThree.setText(String.format(Locale.ENGLISH, "%d", choices.get(2)));
        buttonFour.setText(String.format(Locale.ENGLISH, "%d", choices.get(3)));;

        String questionText = "What is the sum of " + numberOne.toString() + " and " +
                numberTwo.toString() + "?";
        question.setText(questionText);

        }

    public void startBasketball(View view) {
        Intent basketballShooting = new Intent(this, shootBasketball.class);

        startActivity(basketballShooting);
    }

}





