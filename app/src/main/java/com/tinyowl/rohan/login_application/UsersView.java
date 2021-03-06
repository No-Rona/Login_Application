package com.tinyowl.rohan.login_application;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.List;


public class UsersView extends ActionBarActivity {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_view);

        mListView = (ListView)findViewById(R.id.user_list_view);

        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        List<Pair<String, String>> userList = databaseHandler.getAllUsers();
        Cursor cursor = databaseHandler.getDatabaseCursor();

        /*  SimpleCursorAdapter called to populate the ListView with User data  */
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                cursor, new String[] {cursor.getColumnName(2), cursor.getColumnName(4)},
                new int[]{android.R.id.text1, android.R.id.text2}, 0);

        mListView.setAdapter(simpleCursorAdapter);


        /*  Using Custom CursorAdapter to populate the ListView */

        CustomCursorAdapter customCursorAdapter = new CustomCursorAdapter(this, cursor, 0);

        mListView.setAdapter(customCursorAdapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_users_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}
