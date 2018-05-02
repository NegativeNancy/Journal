package com.example.ivodenhertog.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private EntryDatabase db;
    private EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db = EntryDatabase.getInstance(getApplicationContext());
        Cursor cursor = db.selectAll();
        adapter = new EntryAdapter(this, R.layout.entry_row, cursor, 1);


        ListView listView = findViewById(R.id.mainList);
        listView.setAdapter(adapter);

        itemClicked listener = new itemClicked();
        itemLongClicked longListener = new itemLongClicked();
        listView.setOnItemClickListener(listener);
        listView.setOnItemLongClickListener(longListener);


    }

    public void addEntry(View view) {
        Intent input = new Intent(MainActivity.this, InputActivity.class);
        startActivity(input);
    }

    private class itemClicked implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d("ID", "" + position);
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


}
