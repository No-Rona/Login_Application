package com.tinyowl.rohan.login_application;

import android.content.Context;
import android.database.Cursor;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by rohan on 22/07/15.
 */
public class CustomCursorAdapter extends CursorAdapter {

    private LayoutInflater mInflater;


    public CustomCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return mInflater.inflate(android.R.layout.activity_list_item, parent, false);
    }


    //Creating ListView and populating it with the Images that were got form the profile


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ImageView imageView = (ImageView)view.findViewById(android.R.id.icon);
        imageView.setImageResource(cursor.getInt(cursor.getColumnIndex("icon")));

        TextView textView = (TextView)view.findViewById(android.R.id.text1);
        textView.setText(cursor.getString(cursor.getColumnIndex("name")));
    }
}
