package com.example.ivodenhertog.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    // Function to reset requested values.
    public void applyClicked(View view) {
        Switch reset = findViewById(R.id.resetSettings);
        Switch confirm = findViewById(R.id.confirmSettings);

        if (reset.isChecked() && confirm.isChecked()) {
            EntryDatabase clear = EntryDatabase.getInstance(getApplicationContext());
            boolean dbReset = clear.resetDatabase();

            if (!dbReset) {
                Toast.makeText(this, R.string.wrong_toast, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, R.string.clear_toast, Toast.LENGTH_LONG).show();
            }
            finish();
        } else {
            Toast.makeText(this, R.string.confirm_toast, Toast.LENGTH_LONG).show();

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
                // Start new activity to change bio.
                Intent settings = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(settings);
                return(true);
            case R.id.about:
                Toast.makeText(this, R.string.about_toast, Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
