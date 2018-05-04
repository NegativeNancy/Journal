package com.example.ivodenhertog.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EntryDatabase db;
    private EntryAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = EntryDatabase.getInstance(getApplicationContext());
        Cursor cursor = db.selectAll();
        adapter = new EntryAdapter(this, cursor, 1);


        listView = findViewById(R.id.mainList);
        listView.setAdapter(adapter);

        itemClicked listener = new itemClicked();
        itemLongClicked longListener = new itemLongClicked();
        listView.setOnItemClickListener(listener);
        listView.setOnItemLongClickListener(longListener);

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateData();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        listView = findViewById(R.id.mainList);
        int scrollPosition = listView.getLastVisiblePosition();
        Log.d("onSaveInstanceState", "" + scrollPosition);
        outState.putInt("scroll_position",  scrollPosition);
    }
//
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        listView = findViewById(R.id.mainList);
        final int savedPosition = savedInstanceState.getInt("scroll_position");
        listView.smoothScrollToPosition(savedPosition);
    }

    public void addEntry(View view) {
        Intent input = new Intent(MainActivity.this, InputActivity.class);
        startActivity(input);
    }

    private class itemClicked implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Cursor cursor = (Cursor) parent.getItemAtPosition(position);

            JournalEntry detail = new JournalEntry();

            detail.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            detail.setTimestamp(cursor.getString(cursor.getColumnIndex("timestamp")));
            detail.setMood(cursor.getString(cursor.getColumnIndex("mood")));
            detail.setContent(cursor.getString(cursor.getColumnIndex("content")));

            Intent detailActivity = new Intent(MainActivity.this, DetailActivity.class);
            detailActivity.putExtra("journalEntry", detail);
            startActivity(detailActivity);
        }
    }

    private class itemLongClicked implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            EntryDatabase deleteDB = EntryDatabase.getInstance(getApplicationContext());
            deleteDB.delete(id);
            updateData();
            return true;
        }
    }

    private void updateData() {
        Cursor cursor = db.selectAll();
        adapter.swapCursor(cursor);
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
            case R.id.settings:
                // Start new activity to change bio.
                Intent settings = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(settings);
                return(true);
            case R.id.about:
                Toast.makeText(this, R.string.about_toast, Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
