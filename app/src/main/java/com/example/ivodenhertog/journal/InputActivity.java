package com.example.ivodenhertog.journal;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class InputActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    public void angryClicked(View view) {
        ImageButton angry = findViewById(R.id.inputButtonAngry);
        ColorStateList backgroundColor = angry.getBackgroundTintList();

        changeColor(angry, backgroundColor);
    }

    public void sadClicked(View view) {
        ImageButton sad = findViewById(R.id.inputButtonSad);
        ColorStateList backgroundColor = sad.getBackgroundTintList();

        changeColor(sad, backgroundColor);
    }

    public void neutralClicked(View view) {
        ImageButton neutral = findViewById(R.id.inputButtonNeutral);
        ColorStateList backgroundColor = neutral.getBackgroundTintList();

        changeColor(neutral, backgroundColor);
    }

    public void happyClicked(View view) {
        ImageButton happy = findViewById(R.id.inputButtonHappy);
        ColorStateList backgroundColor = happy.getBackgroundTintList();

        changeColor(happy, backgroundColor);
    }

    public void changeColor(ImageButton btn, ColorStateList backgroundColor) {
        ColorStateList dark = ColorStateList.valueOf(getResources().getColor(R.color.holo_green_dark));
        ColorStateList light = ColorStateList.valueOf(getResources().getColor(R.color.holo_green_light));

        if (backgroundColor == light) {
            btn.setBackgroundTintList(dark);
        } else {
            btn.setBackgroundTintList(light);
        }
    }
}
