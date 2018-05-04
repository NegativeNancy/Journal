package com.example.ivodenhertog.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent detail = getIntent();
        loadDetails(detail);
    }

    private void loadDetails(Intent intent) {
        JournalEntry entry = (JournalEntry) intent.getSerializableExtra("journalEntry");

        TextView titleView = findViewById(R.id.detailTitle);
        TextView dateView = findViewById(R.id.detailDate);
        TextView moodView = findViewById(R.id.detailMood);
        TextView contentView = findViewById(R.id.detailMessage);
        ImageView moodImage = findViewById(R.id.detailImage);

        String title = entry.getTitle();
        String date = entry.getTimestamp();
        String mood = entry.getMood();
        String content = entry.getContent();

        titleView.setText(title);
        dateView.setText(date);
        moodView.setText(mood);
        contentView.setText(content);


        if (mood != null) {
            if (mood.contains("angry")) {
                moodImage.setImageDrawable(getDrawable(R.drawable.angry));
            } else if (mood.contains("sad")) {
                moodImage.setImageDrawable(getDrawable(R.drawable.sad));
            } else if (mood.contains("neutral")) {
                moodImage.setImageDrawable(getDrawable(R.drawable.neutral));
            } else if (mood.contains("happy")) {
                moodImage.setImageDrawable(getDrawable(R.drawable.happy));
            } else {
                moodImage.setImageDrawable(getDrawable(R.drawable.neutral));
            }
        }
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
                Intent main = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(main);
                return(true);
            case R.id.settings:
                // Start new activity to change bio.
                Intent settings = new Intent(DetailActivity.this, SettingsActivity.class);
                startActivity(settings);
                return(true);
            case R.id.about:
                Toast.makeText(this, R.string.about_toast, Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
