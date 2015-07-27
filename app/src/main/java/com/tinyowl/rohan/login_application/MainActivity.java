package com.tinyowl.rohan.login_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends ActionBarActivity {

    @Bind(R.id.username_text)
    EditText mUsername;
    @Bind(R.id.password_text)
    EditText mPassword;
    @Bind(R.id.login_button)
    Button mLogin;
    @Bind(R.id.signup_button)
    Button mSignUp;
    @Bind(android.R.id.content)
    View mMainContentView;

    DatabaseHandler mDatabaseHandler;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mDatabaseHandler = new DatabaseHandler(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.login_button)
    public void checkUser() {

        if (isEmpty(mUsername) || isEmpty(mPassword)) {
            Snackbar.make(mMainContentView, R.string.username_error_snack, Snackbar.LENGTH_SHORT).show();
            if (isEmpty(mUsername)) YoYo.with(Techniques.Shake).duration(1000).playOn(mUsername);
            else YoYo.with(Techniques.Shake).duration(1000).playOn(mPassword);
            return;
        }

        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();

        if (mDatabaseHandler.checkUser(username, password)) {
            Snackbar.make(mMainContentView, R.string.login_success , Snackbar.LENGTH_SHORT).show();
            Intent intent = new Intent(this, UsersView.class);
            startActivity(intent);
        }
        else {
            Snackbar.make(mMainContentView, R.string.password_error_snack, Snackbar.LENGTH_SHORT).show();
        }

    }

    public boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    @OnClick(R.id.signup_button)
    public void sendToSignUp() {

        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    public int getNumber() {
        return 0;
    }



}
