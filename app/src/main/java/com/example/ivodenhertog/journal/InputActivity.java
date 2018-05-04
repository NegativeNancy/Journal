package com.example.ivodenhertog.journal;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity {

    private String mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String title = ((TextView) findViewById(R.id.inputTitle)).getText().toString();
        String content = ((TextView) findViewById(R.id.inputField)).getText().toString();

        outState.putString("title", title);
        outState.putString("content", content);
        outState.putString("mood", mood);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String title = savedInstanceState.getString("title");
        String content = savedInstanceState.getString("content");
        mood = savedInstanceState.getString("mood");

        TextView titleView = findViewById(R.id.inputTitle);
        TextView contentView = findViewById(R.id.inputField);
        ImageButton button;

        titleView.setText(title);
        contentView.setText(content);

        if (mood.contains("angry")) {
            button = findViewById(R.id.inputButtonAngry);
        } else if (mood.contains("sad")) {
            button = findViewById(R.id.inputButtonSad);
        } else if (mood.contains("neutral")) {
            button = findViewById(R.id.inputButtonNeutral);
        } else if (mood.contains("happy")) {
            button = findViewById(R.id.inputButtonHappy);
        } else {
            button = findViewById(R.id.inputButtonNeutral);
        }

        ColorStateList backgroundColor = button.getBackgroundTintList();
        changeColor(button, backgroundColor);
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

    private void changeColor(ImageButton btn, ColorStateList backgroundColor) {
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

    // Function to create on-screen menu.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return(true);
    }

    // Function that handles the events when menu button is pressed.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back:
                Intent main = new Intent(InputActivity.this, MainActivity.class);
                startActivity(main);
                return(true);
            case R.id.settings:
                // Start new activity to change bio.
                Intent settings = new Intent(InputActivity.this, SettingsActivity.class);
                startActivity(settings);
                return(true);
            case R.id.about:
                Toast.makeText(this, R.string.about_toast, Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
