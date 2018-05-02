package com.example.ivodenhertog.journal;

import android.content.res.ColorStateList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity {

    private String mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    public void moodClicked(View view) {
        int moodId = view.getId();

        ImageButton button = findViewById(moodId);
        ColorStateList backgroundColor = button.getBackgroundTintList();
        changeColor(button, backgroundColor);

        switch (moodId) {
            case R.id.inputButtonAngry:
                mood = "angry";
                break;
            case R.id.inputButtonSad:
                mood = "sad";
                break;
            case R.id.inputButtonNeutral:
                mood = "neutral";
                break;
            case R.id.inputButtonHappy:
                mood = "happy";
                break;
        }
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


    public void inputSubmitClicked(View view) {
        JournalEntry entry = new JournalEntry();
        EntryDatabase insertDB = EntryDatabase.getInstance(this);

        String title = ((TextView) findViewById(R.id.inputTitle)).getText().toString();
        String content = ((TextView) findViewById(R.id.inputField)).getText().toString();

        entry.setTitle(title);
        entry.setContent(content);
        entry.setMood(mood);

        insertDB.insert(entry);

        finish();
    }
}
