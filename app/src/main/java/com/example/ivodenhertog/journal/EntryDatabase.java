package com.example.ivodenhertog.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EntryDatabase extends SQLiteOpenHelper {

    private static EntryDatabase instance;
    private static final String dbName = "entries";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE entries (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, content TEXT, mood TEXT, timestamp DATETIME DEFAULT CURRENT_TIMESTAMP);");

        String entry1 = "INSERT INTO entries (title, content, mood, timestamp) VALUES (\"Test " +
                "entry 1\", \"This is a test message for test entry 1\", \"happy\");";
        String entry2 = "INSERT INTO entries (title, content, mood, timestamp) VALUES (\"Test" +
                "entry 2\", \"This is a test message for test entry 2\", \"happy\");";
        String entry3 = "INSERT INTO entries (title, content, mood, timestamp) VALUES (\"Test " +
                "entry 3\", \"This is a test message for test entry 3\", \"happy\");";

        db.execSQL(entry1);
        db.execSQL(entry2);
        db.execSQL(entry3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "entries");
        onCreate(db);
    }

    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static EntryDatabase getInstance(Context context) {
        if (instance != null) {
            return instance;
        } else {
            instance = new EntryDatabase(context, dbName, null, 1);
            return instance;
        }
    }

    public Cursor selectAll() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + dbName, null);
        return cursor;
    }

    public void insert(JournalEntry entry) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("title", entry.getTitle());
        content.put("content", entry.getContent());
        content.put("mood", entry.getMood());

        db.insert(dbName, null, content);
    }

    public void delete(Long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(dbName, "_id=" + id.toString(), null);
    }
}
