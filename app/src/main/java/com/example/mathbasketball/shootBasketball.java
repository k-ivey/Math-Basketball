package com.example.mathbasketball;

import android.support.animation.DynamicAnimation;
import android.support.animation.FlingAnimation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class shootBasketball extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoot_basketball);
    }

    public ImageView basketball = (ImageView) findViewById(R.id.basketball);

    public boolean onFling() {

        FlingAnimation flingBasketball = new FlingAnimation(basketball, DynamicAnimation.SCROLL_X);
        flingBasketball.setStartVelocity(10);

        return true;
    }

}

