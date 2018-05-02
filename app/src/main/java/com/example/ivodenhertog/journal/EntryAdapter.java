package com.example.ivodenhertog.journal;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class EntryAdapter extends ResourceCursorAdapter {
    public EntryAdapter(Context context, int layout, Cursor c, int flags) {
        super(context, R.layout.entry_row, c, flags);
    }

    public void bindView(View view, Context context, Cursor cursor) {
        int _idInt = cursor.getColumnIndex("_id");
        int titleInt = cursor.getColumnIndex("title");
        int dateInt = cursor.getColumnIndex("timestamp");
        int moodInt = cursor.getColumnIndex("mood");

        long id = cursor.getLong(_idInt);
        String title = cursor.getString(titleInt);
        String date = cursor.getString(dateInt);
        String mood = cursor.getString(moodInt);

        TextView titleView = view.findViewById(R.id.entryTitle);
        TextView dateView = view.findViewById(R.id.entryDate);
        TextView moodView = view.findViewById(R.id.entryMood);
        ImageView moodImage = view.findViewById(R.id.moodImage);

        titleView.setText(title);
        dateView.setText(date);
        moodView.setText(mood);

        if (mood != null) {
            if (mood.contains("angry")) {
                moodImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.angry));
            } else if (mood.contains("sad")) {
                moodImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.sad));
            } else if (mood.contains("neutral")) {
                moodImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.neutral));
            } else if (mood.contains("happy")) {
                moodImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.happy));
            } else {
                moodImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.neutral));
            }
        }
    }
}
