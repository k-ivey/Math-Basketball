package com.example.mathbasketball;

import android.os.Bundle;
import android.support.animation.DynamicAnimation;
import android.support.animation.FlingAnimation;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


public class shootBasketball extends AppCompatActivity {

    private ImageView basketball;
    //private ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoot_basketball);

        basketball = findViewById(R.id.basketball);
        //mainLayout = (ConstraintLayout) findViewById(R.id.main_layout);

        final GestureDetector gestureDetector = new GestureDetector(this, mGestureListener);

        basketball.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    private GestureDetector.OnGestureListener mGestureListener = new GestureDetector.SimpleOnGestureListener() {

        //Constants
        private static final int MIN_DISTANCE_MOVED = 20;
        private static final float MIN_TRANSLATION = 0;
        private static final float FRICTION = 1.0f;
        private int maxTranslationX = 1000;
        private int maxTranslationY = 1900;

        @Override
        public boolean onDown(MotionEvent event) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY) {
            //downEvent : when user puts his finger down on the view
            //moveEvent : when user lifts his finger at the end of the movement
            float distanceInX = Math.abs(moveEvent.getRawX() - downEvent.getRawX());
            float distanceInY = Math.abs(moveEvent.getRawY() - downEvent.getRawY());

            if (distanceInX > MIN_DISTANCE_MOVED) {
                //Fling Right/Left
                FlingAnimation flingX = new FlingAnimation(basketball, DynamicAnimation.TRANSLATION_X);
                flingX.setStartVelocity(velocityX)
                        .setMinValue(MIN_TRANSLATION) // minimum translationX property
                        .setMaxValue(maxTranslationX)  // maximum translationX property
                        .setFriction(FRICTION)
                        .start();
            } else if (distanceInY > MIN_DISTANCE_MOVED) {
                //Fling Down/Up
                FlingAnimation flingY = new FlingAnimation(basketball, DynamicAnimation.TRANSLATION_Y);
                flingY.setStartVelocity(velocityY)
                        .setMinValue(MIN_TRANSLATION)  // minimum translationY property
                        .setMaxValue(maxTranslationY) // maximum translationY property
                        .setFriction(FRICTION)
                        .start();
            }

            return true;
        }
    };
}
